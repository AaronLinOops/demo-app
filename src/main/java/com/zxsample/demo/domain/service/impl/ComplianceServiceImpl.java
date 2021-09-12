package com.zxsample.demo.domain.service.impl;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.service.ComplianceChecker;
import com.zxsample.demo.domain.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合规检查服务
 */
@Service
public class ComplianceServiceImpl implements ComplianceService {

    @Autowired
    private List<ComplianceChecker> checkerList;

    @Override
    public boolean check(Inventory inventory) {
        for (ComplianceChecker complianceChecker : checkerList) {
            if (!complianceChecker.check(inventory)) {
                return false;
            }
        }
        return true;
    }
}
