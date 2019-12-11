package com.tbz.payment;

import org.junit.Before;

public class TestEnvironment {
    @Before
    public void testBefore() {
        System.getProperties().put("root.dir", "/Users/tbz/Payment");
    }
}
