package com.zxsample.demo.infrastructure.domain.factory.impl;

import com.google.common.base.Preconditions;
import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.factory.InventoryFactory;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InventoryFactoryImpl implements InventoryFactory {

    @Override
    public Inventory create(String name, Integer count, InventoryDetail detail) {
        return create(null, name, count, detail);
    }

    @Override
    public Inventory create(String bizId, String name, Integer count, InventoryDetail detail) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(count, "count cannot be null");
        Preconditions.checkNotNull(detail, "detail cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "name cannot be empty");
        Preconditions.checkArgument(count >= 0, "required count >= 0");

        return Inventory.builder()
                .id(newId(bizId))
                .name(name)
                .remains(count)
                .detail(detail)
                .build();
    }

    private InventoryId newId(String bizId) {
        if (StringUtils.isEmpty(bizId)) {
            return InventoryId.of(UUID.randomUUID().toString());
        }
        return InventoryId.of(bizId);
    }
}
