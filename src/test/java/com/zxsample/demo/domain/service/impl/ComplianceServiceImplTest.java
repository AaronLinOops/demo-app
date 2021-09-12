package com.zxsample.demo.domain.service.impl;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.factory.InventoryFactory;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.service.ComplianceChecker;
import com.zxsample.demo.domain.service.ComplianceService;
import com.zxsample.demo.test.WithSpringTestBase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ComplianceServiceImplTest extends WithSpringTestBase {

    @Autowired
    private InventoryFactory factory;

    @MockBean(name="remoteComplianceChecker")
    private ComplianceChecker complianceChecker;

    @Autowired
    private ComplianceService complianceService;

    @Test
    public void testNormal() throws Exception {
        Inventory inventory = factory.create("INV-name1", 100,
                InventoryDetail.builder().description("test-comment").build());

        // 在这里通过 Mockito 框架对 RemoteComplianceCheckerImpl 的 check() 方法打桩
        Mockito.doReturn(true)
                .when(complianceChecker).check(inventory);

        Assertions.assertThatCode(() -> complianceService.check(inventory))
                .doesNotThrowAnyException();
    }

    @Test
    public void testExceptions() throws Exception {
        Inventory inventory = factory.create("not-compliance", 100,
                InventoryDetail.builder().description("test-comment").build());

        Mockito.doReturn(false)
                .when(complianceChecker).check(inventory);

        Assertions.assertThat(complianceService.check(inventory)).isEqualTo(false);
    }
}