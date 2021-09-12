package com.zxsample.demo.domain.service;

import com.zxsample.demo.domain.entity.Inventory;

/**
 * 合规检查
 */
public interface ComplianceChecker {

    boolean check(Inventory inventory);
}
