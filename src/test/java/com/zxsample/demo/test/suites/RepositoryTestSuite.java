package com.zxsample.demo.test.suites;

import com.zxsample.demo.infrastructure.domain.repository.impl.InventoryRepositoryImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        InventoryRepositoryImplTest.class
})
public class RepositoryTestSuite {
}
