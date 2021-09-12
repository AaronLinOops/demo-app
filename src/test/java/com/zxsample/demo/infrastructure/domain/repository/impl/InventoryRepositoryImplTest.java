package com.zxsample.demo.infrastructure.domain.repository.impl;


import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.factory.InventoryFactory;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;
import com.zxsample.demo.domain.repository.InventoryRepository;
import com.zxsample.demo.test.WithSpringTestBase;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.hamcrest.Matchers;

@Transactional
public class InventoryRepositoryImplTest extends WithSpringTestBase {

    @Autowired
    private InventoryFactory factory;

    @Autowired
    private InventoryRepository repository;

    @Test
    public void testNormal() throws Exception {
        // 创建一个库存实体对象
        Inventory inventory = factory.create("test", 100,
                InventoryDetail.builder().description("test-comment").build());
        // 保存
        InventoryId inventoryId = repository.save(inventory);
        Assertions.assertThat(inventoryId).isNotNull();
        Assertions.assertThat(inventoryId.getBizId()).isNotEmpty();
        // 查找刚才保存的实体对象，比较查出来的结果和实际的是否一致
        Inventory inventory1 = repository.findById(inventoryId);
        Assertions.assertThat(inventory1).isNotNull();
        MatcherAssert.assertThat("inventory not matched", inventory1, Matchers.samePropertyValuesAs(inventory));
        // 更新实体
        inventory.setName("new_name");
        inventory.getDetail().setDescription("new description");
        InventoryId inventoryId1 = repository.save(inventory);
        Assertions.assertThat(inventoryId1).isEqualTo(inventoryId);
        // 检查是否更新成功
        Inventory inventory2 = repository.findById(inventoryId1);
        MatcherAssert.assertThat("inventory not matched", inventory2, Matchers.samePropertyValuesAs(inventory));
        // 删除实体对象，检查是否删除成功
        repository.deleteById(inventoryId);
        Inventory inventory3 = repository.findById(inventoryId);
        Assertions.assertThat(inventory3).isNull();
    }

    @Test
    public void testExceptions() throws Exception {

        Assertions.assertThatCode(() -> {
            factory.create(null, 100,
                    InventoryDetail.builder().description("test-comment").build());
        }).hasMessageContaining("name cannot be null");

        Assertions.assertThatCode(() -> {
            factory.create("name1", null,
                    InventoryDetail.builder().description("test-comment").build());
        }).hasMessageContaining("count cannot be null");

        Assertions.assertThatCode(() -> {
            factory.create("name1", 100, null);
        }).hasMessageContaining("detail cannot be null");

        Assertions.assertThatCode(() -> {
            factory.create("", 100,
                    InventoryDetail.builder().description("test-comment").build());
        }).hasMessageContaining("name cannot be empty");

        Assertions.assertThatCode(() -> {
            factory.create("name1", -1,
                    InventoryDetail.builder().description("test-comment").build());
        }).hasMessageContaining("required count >= 0");

        InventoryId inventoryId = repository.save(null);
        Assertions.assertThat(inventoryId).isNull();

        Inventory inventory = repository.findById(null);
        Assertions.assertThat(inventory).isNull();
        Assertions.assertThatCode(() -> repository.deleteById(null))
                .doesNotThrowAnyException();
    }
}