package com.zxsample.demo.domain.factory;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.model.InventoryDetail;

/**
 * 实体工厂
 */
public interface InventoryFactory {

    Inventory create(String name, Integer count, InventoryDetail detail);

    Inventory create(String bizId, String name, Integer count, InventoryDetail detail);
}
