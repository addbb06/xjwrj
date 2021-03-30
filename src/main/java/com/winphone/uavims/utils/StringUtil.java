package com.winphone.xjwrj.utils;

/**
 * 字符串工具类
 *
 * @author jack
 */
public class StringUtil {

    public static boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    public static boolean equals(String text1, String text2) {
        return text1 == null ? text2 == null : text1.equals(text2);
    }

    public static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1)
                result.append("0");
            result.append(hex);
        }
        return result.toString();
    }
}
