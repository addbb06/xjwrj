package com.winphone.xjwrj.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/18
 * @Modified By:
 */
public class GeneratorIds {
    public static String[] chars = new String[]
            {
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            };

    /**
     * @param columnId     课程栏目id
     * @param payType      支付方式
     * @param serialNumber 流水号，需要+1处理
     * @return
     */
    public static String getOrderNumber(Long columnId, String payType, int serialNumber) {
        return DateUtils.getDateNumber()
                + columnId
                + payType
                + String.format("%05d", serialNumber + 1);
    }

    /**
     * 生成纯数字随机码
     * @param strLength 字符串长度
     * @return 长度为【strLength】的随机数
     */
    private static String getFixLengthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串
        String fixLengthString = new BigDecimal(pross).toString();
        // 返回固定的长度的随机数
        return fixLengthString.substring(1, strLength + 1);
    }

    /**
     * 生成随机标识码
     *
     * @param prefix 前缀
     * @param length 中间随机字符长度
     * @param suffix 后缀
     * @return 生成的uuid
     */
    public static String getShortUuid(String prefix, int length, String suffix) {
        StringBuilder stringBuffer = new StringBuilder();
        if (StringUtils.isNotEmpty(prefix)) {
            stringBuffer.append(prefix);
        }

        //取UUID 去除-
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //指定长度截取
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int strInteger = Integer.parseInt(str, 16);
            stringBuffer.append(chars[strInteger % 0x3E]);
        }

        if (StringUtils.isNotEmpty(suffix)) {
            stringBuffer.append(suffix);
        }
        return stringBuffer.toString();
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length     位数
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

}
