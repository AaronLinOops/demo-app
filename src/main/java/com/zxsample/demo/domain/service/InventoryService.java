package com.zxsample.demo.domain.service;

import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;

/**
 * 库存服务
 */
public interface InventoryService {

    InventoryId createInventory(String name, Integer count, InventoryDetail detail);

    void changeCount(InventoryId inventoryId, Integer count);
}
