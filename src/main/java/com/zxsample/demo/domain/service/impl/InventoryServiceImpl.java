package com.zxsample.demo.domain.service.impl;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.factory.InventoryFactory;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;
import com.zxsample.demo.domain.repository.InventoryRepository;
import com.zxsample.demo.domain.service.ComplianceService;
import com.zxsample.demo.domain.service.InventoryService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryFactory inventoryFactory;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ComplianceService complianceService;

    @Transactional
    @Override
    public InventoryId createInventory(String name, Integer count, InventoryDetail detail) {
        Inventory inventory = inventoryFactory.create(name, count, detail);
        if (!complianceService.check(inventory)) {
            throw new RuntimeException("Inventory is not compliance, name=" + name);
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public void changeCount(InventoryId inventoryId, Integer count) {
        Preconditions.checkNotNull(count, "Count cannot be NULL");
        Preconditions.checkArgument(count >= 0, "Count should >= 0");
        Inventory inventory = inventoryRepository.findById(inventoryId);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found, id=" + inventoryId);
        }
        inventory.setRemains(count);
        inventoryRepository.save(inventory);
    }
}
