package com.tbz.payment;

import org.junit.Before;

public class TestEnvironment {

    /**
     * 测试用例公用的前置条件
     */
    @Before
    public void testBefore() {
        // 避免log4j2无法获取System.getProperties()而发生异常
        System.getProperties().put("root.dir", "/Users/tbz/Payment");
    }
}
