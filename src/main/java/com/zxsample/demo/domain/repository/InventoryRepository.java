package com.zxsample.demo.domain.repository;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.model.InventoryId;

/**
 * 库存实体仓库 接口定义
 */
public interface InventoryRepository {

    /**
     * 通过 id 查找并加载实体对象.
     *
     * @param inventoryId
     * @return null if not exits
     */
    Inventory findById(InventoryId inventoryId);

    /**
     * 保存或更新实体对象.
     *
     * @param inventory
     * @return
     */
    InventoryId save(Inventory inventory);

    /**
     * 根据 id 删除实体对象.
     *
     * @param inventoryId
     */
    void deleteById(InventoryId inventoryId);
}
