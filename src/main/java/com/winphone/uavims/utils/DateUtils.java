package com.winphone.xjwrj.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    // ================================================================
    // Constants
    // ================================================================

    private static String[] parsePatterns = {
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM",
            "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm",
            "yyyy/MM",
            "yyyy.MM.dd",
            "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm",
            "yyyy.MM"};

    /**
     * 友好时间展示样式
     */
    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";


    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDateTimeNumber() {
        return getDate("yyyyMMddHHmmss");
    }

    /**
     * 得到当前日期字符串 格式（yyyyMMdd）
     */
    public static String getDateNumber() {
        return getDate("yyyyMMdd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期字符串，转换格式（yyyy-MM-dd）
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd）
     */
    public static String getNowDate() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * <pre>
     *
     * 日期型字符串转化为日期 格式
     * {
     * 		"yyyy-MM-dd",
     * 		"yyyy-MM-dd HH:mm:ss",
     * 		"yyyy-MM-dd HH:mm",
     * 		"yyyy/MM/dd",
     * 		"yyyy/MM/dd HH:mm:ss",
     *  	"yyyy/MM/dd HH:mm",
     *  	"yyyy.MM.dd",
     *  	"yyyy.MM.dd HH:mm:ss",
     *  	"yyyy.MM.dd HH:mm"
     * }
     *
     * <pre>
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date 过去时间
     * @return 天数
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000L);
    }

    /**
     * 获取过去的小时
     *
     * @param date 过去时间
     * @return 小时数
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000L);
    }

    /**
     * 获取过去的分钟
     *
     * @param date 过期时间
     * @return 分钟数
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 时刻
     * @return 格式化后时间
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000L);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60L - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000L - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 时刻
     * @return 格式化后时间
     */
    public static String formatDateTimeLocal(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000L);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60L - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "天" : "") + hour + "小时" + min + "分" + s + "秒";
    }


    /**
     * 获取两个日期之间的月数
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 间隔
     */
    public static int getDistanceMonthOfTwoDate(Date before, Date after) {
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(before);
        aft.setTime(after);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return (Math.abs(month + result));
    }


    /**
     * 获取两个日期之间的天数
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 间隔
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24L);
    }

    /**
     * 获取两个日期之间的小时数
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 间隔
     */
    public static double getDistanceHourOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60L);
    }
    /**
     * 获取两个日期之间的小时数
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 间隔
     */
    public static double getDistanceMinutesOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60L);
    }

    /**
     * 友好时间格式化
     */
    public static String relativeDateFormat(Date dateTime) {
        if (dateTime == null) {
            return "";
        }
        long delta = System.currentTimeMillis() - dateTime.getTime();
        if (delta < 60 * 1000L) {
            long seconds = delta / 1000L;
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45 * 60 * 1000L) {
            long minutes = delta / 1000L / 60L;
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * 60 * 60 * 1000L) {
            long hours = delta / 1000L / 60L / 60L;
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * 60 * 60 * 1000L) {
            return "昨天";
        }
        if (delta < 30L * 24L * 60 * 60 * 1000L) {
            long days = delta / 1000L / 60L / 60L / 24L;
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * 24L * 60 * 60 * 1000L * 7) {
            long months = delta / 1000L / 60L / 60L / 24L / 30L;
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = delta / 1000L / 60L / 60L / 24L / 365L;
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    /**
     *  此方法要求end 大于start
     * @param start
     * @param end
     * @return
     */
    public static Long getDistanceOfTwoSecond(Date start,Date end){
        return end.getTime() - start.getTime();
    }

    /**
     *  时间增加 多少秒
     * @param date
     * @param secondBase
     * @return
     */
    public static Date nextGivenSecondDate(Date date, int secondBase) {
        if (secondBase >= 0 && secondBase <= 59) {
            if (date == null) {
                date = new Date();
            }

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.setLenient(true);
            if (secondBase == 0) {
                c.set(12, c.get(12) + 1);
                c.set(13, 0);
                c.set(14, 0);
                return c.getTime();
            } else {
                int second = c.get(13);
                int arItr = second / secondBase;
                int nextSecondOccurance = secondBase * (arItr + 1);
                if (nextSecondOccurance < 60) {
                    c.set(13, nextSecondOccurance);
                    c.set(14, 0);
                    return c.getTime();
                } else {
                    c.set(12, c.get(12) + 1);
                    c.set(13, 0);
                    c.set(14, 0);
                    return c.getTime();
                }
            }
        } else {
            throw new IllegalArgumentException("secondBase must be >=0 and <= 59");
        }
    }

    /**
     * 获取当天最晚时间
     * @param date
     * @return
     */
    public static String getLastDate(Date date){
        //一天的毫秒-1
        int dayMis = 1000*60*60*24;
        //当天的毫秒
        long curMillisecond = date.getTime();
        //当天最后一秒
        long resultMis = curMillisecond+(dayMis-1);
        Date resultDate = new Date(resultMis);
        return formatDateTime(resultDate);
    }

}
