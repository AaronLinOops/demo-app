package com.zxsample.demo.infrastructure.domain.service.impl;

import com.zxsample.demo.domain.entity.Inventory;
import com.zxsample.demo.domain.service.ComplianceChecker;
import org.springframework.stereotype.Component;

/**
 * 假设这里需要调用其他团队维护的合规规约检查服务来进行检查，
 * 所以在单元测试的时候，需要进行 Mock .
 */
@Component("remoteComplianceChecker")
public class RemoteComplianceCheckerImpl implements ComplianceChecker {

    @Override
    public boolean check(Inventory inventory) {
        // TODO：XXX 团队还没有开发完成，等它们开发完成后再联调
        throw new RuntimeException("XXX 团队还没有开发完成，等它们开发完成后再联调");
    }
}
