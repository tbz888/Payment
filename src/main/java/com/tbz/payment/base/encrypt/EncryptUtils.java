package com.tbz.payment.base.encrypt;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;

public class EncryptUtils {

    // 默认的对称秘钥
    private static final String DEFAULT_KEY = "com.tbz.payment";

    // 算法名称/加密模式/数据填充方式
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content    加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encryptAES(String content, String encryptKey) throws Exception {
        encryptKey = normalizeAESKey(encryptKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    public static String encryptAES(String content) throws Exception {
        return encryptAES(content, DEFAULT_KEY);
    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decryptAES(String encryptStr, String decryptKey) throws Exception {
        decryptKey = normalizeAESKey(decryptKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String decryptAES(String encryptStr) throws Exception {
        return decryptAES(encryptStr, DEFAULT_KEY);
    }

    /**
     * 统一以32位作为私钥的标准长度
     *
     * @param key
     * @return
     */
    private static String normalizeAESKey(String key) {
        if (StringUtils.isEmpty(key)) return key;
        int length = key.length();
        if (length < 32) {
            return key + String.join("", Collections.nCopies(32 - length, "1"));
        } else {
            return key.substring(0, 32);
        }
    }

}
