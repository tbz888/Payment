package com.tbz.payment.base.encrypt;

import com.tbz.payment.TestEnvironment;
import org.junit.Assert;
import org.junit.Test;


public class EncryptUtilsTest extends TestEnvironment {
    @Test
    public void testBase() throws Exception {
        String origin = "qwertyuiop";
        String en = EncryptUtils.encryptAES(origin);
        String de = EncryptUtils.decryptAES(en);
        Assert.assertEquals(origin, de);
    }

    @Test
    public void testKey() throws Exception {
        String origin = "qwertyuiop";
        String key = "1234567890123456";
        String en = EncryptUtils.encryptAES(origin, key);
        String de = EncryptUtils.decryptAES(en, key);
        Assert.assertEquals(origin, de);
    }

    @Test
    public void testKeyLess32Length() throws Exception {
        String origin = "qw";
        String key = "123456";
        String en = EncryptUtils.encryptAES(origin, key);
        String de = EncryptUtils.decryptAES(en, key);
        Assert.assertEquals(origin, de);
    }

    @Test
    public void testKeyEqual32Length() throws Exception {
        String origin = "qw";
        String key = "12345678123456781234567812345678";
        String en = EncryptUtils.encryptAES(origin, key);
        String de = EncryptUtils.decryptAES(en, key);
        Assert.assertEquals(origin, de);
    }

    @Test
    public void testKeyGreater32Length() throws Exception {
        String origin = "qw";
        String key = "12345678123456781234567812345679876567865748";
        String en = EncryptUtils.encryptAES(origin, key);
        String de = EncryptUtils.decryptAES(en, key);
        Assert.assertEquals(origin, de);
    }
}
