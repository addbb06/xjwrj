package com.winphone.xjwrj.utils;

import java.security.MessageDigest;

/**
 * 消息摘要工具类
 *
 * @author jack
 */
public class MessageDigestUtil {

    /**
     * 获取摘要（MD5、SHA-1、SHA-256）
     */
    public static String digest(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            return StringUtil.toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
