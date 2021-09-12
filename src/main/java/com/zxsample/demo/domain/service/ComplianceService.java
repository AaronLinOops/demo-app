package com.zxsample.demo.domain.service;

import com.zxsample.demo.domain.entity.Inventory;

/**
 * 合规检查服务
 */
public interface ComplianceService {

    /**
     * 检查是否合规.
     * @param inventory
     * @return
     */
    boolean check(Inventory inventory);
}
