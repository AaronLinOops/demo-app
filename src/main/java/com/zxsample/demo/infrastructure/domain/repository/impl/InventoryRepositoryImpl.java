package com.zxsample.demo.infrastructure.domain.repository.impl;

import com.google.gson.Gson;
import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.factory.InventoryFactory;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;
import com.zxsample.demo.domain.repository.InventoryRepository;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDO;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDOExample;
import com.zxsample.demo.infrastructure.dal.mapper.InventoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private InventoryFactory inventoryFactory;

    @Autowired
    private InventoryDOMapper mapper;

    @Override
    public Inventory findById(InventoryId inventoryId) {
        if (inventoryId == null) {
            return null;
        }
        InventoryDO inventoryDO = mapper.selectOneByExampleWithBLOBs(makeQuery(inventoryId));
        return fromInventoryDO(inventoryDO);
    }

    @Override
    public InventoryId save(Inventory inventory) {
        InventoryDO inventoryDO = toInventoryDO(inventory);
        if (inventoryDO == null) {
            return null;
        }
        mapper.upsertWithBLOBs(inventoryDO);
        return inventory.getId();
    }

    @Override
    public void deleteById(InventoryId inventoryId) {
        if (inventoryId == null) {
            return;
        }
        mapper.deleteByExample(makeQuery(inventoryId));
    }

    private InventoryDOExample makeQuery(InventoryId inventoryId) {
        return new InventoryDOExample().createCriteria()
                .andBizIdEqualTo(inventoryId.getBizId())
                .example();
    }

    private Inventory fromInventoryDO(InventoryDO inventoryDO) {
        if (inventoryDO == null) {
            return null;
        }
        InventoryDetail detail = new Gson().fromJson(inventoryDO.getDetail(), InventoryDetail.class);
        return inventoryFactory.create(inventoryDO.getBizId(), inventoryDO.getName(), inventoryDO.getCount(), detail);
    }

    private InventoryDO toInventoryDO(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        return InventoryDO.builder()
                .bizId(inventory.getId().getBizId())
                .name(inventory.getName())
                .count(inventory.getRemains())
                .detail(new Gson().toJson(inventory.getDetail()))
                .build();
    }
}
