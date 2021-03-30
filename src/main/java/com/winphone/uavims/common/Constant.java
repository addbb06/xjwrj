package com.winphone.xjwrj.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhou
 * @Description:  定义系统的一些常量
 * @Date:Create in 2017/11/10
 * @Modified By:
 */

public class Constant {
    /**
     *  成功、失败
     */
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    /**
     * 账户类型
     */
    public static final String SYSUSER_TYPE_SYSTEM = "SYSTEM_ADMIN";    //平台级
    public static final String SYSUSER_TYPE_COMPANY = "COMPANY_ADMIN";    //公司级

    /**
     * 用户状态
     */
    public static final String SYSUSER_STATE_1 = "1";    //正常
    public static final String SYSUSER_STATE_2 = "2";    //锁定

    /**
     * 用户性别
     */
    public static final String USER_SEX_UNKNOWN = "0";    //未知
    public static final String USER_SEX_MAN = "1";    //男
    public static final String USER_SEX_WOMAN = "2";    //女


    /**默认未删除**/
    public static final String DELETE_NO = "0";
    public static final String DELETE_YES = "1";

    /**
     * 默认消息未读
     **/
    public static final String MESSAGE_READ_NO = "0";
    public static final String MESSAGE_READ_YES = "1";

    /**
     * 允许上传的图片最大大小(单位为byte))
     */
    public static final int MAX_IMAGE_SIZE = 3 * 1024 * 1024;
    /**
     * 允许上传的图片格式
     */
    public final static List<String> IMAGE_TYPE_LIST = new ArrayList<>();
    static {
        IMAGE_TYPE_LIST.add("jpg");
        IMAGE_TYPE_LIST.add("png");
        IMAGE_TYPE_LIST.add("gif");
        IMAGE_TYPE_LIST.add("bmp");
    }


    /**所有文件格式*/
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();
    static{
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); //JPEG
        FILE_TYPE_MAP.put("png", "89504E47"); //PNG
        FILE_TYPE_MAP.put("gif", "47494638"); //GIF
        FILE_TYPE_MAP.put("tif", "49492A00"); //TIFF
        FILE_TYPE_MAP.put("bmp", "424D"); //Windows Bitmap
        FILE_TYPE_MAP.put("dwg", "41433130"); //CAD
        FILE_TYPE_MAP.put("html", "68746D6C3E"); //HTML
        FILE_TYPE_MAP.put("rtf", "7B5C727466"); //Rich Text Format
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053"); //PhotoShop
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); //Email [thorough only]
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); //Outlook Express
        FILE_TYPE_MAP.put("pst", "2142444E"); //Outlook
        FILE_TYPE_MAP.put("office", "D0CF11E0"); //office类型，包括doc、xls和ppt
        FILE_TYPE_MAP.put("mdb", "000100005374616E64617264204A"); //MS Access
        FILE_TYPE_MAP.put("wpd", "FF575043"); //WordPerfect
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E"); //Adobe Acrobat
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); //Quicken
        FILE_TYPE_MAP.put("pwl", "E3828596"); //Windows Password
        FILE_TYPE_MAP.put("wav", "57415645"); //Wave
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD"); //Real Audio
        FILE_TYPE_MAP.put("rm", "2E524D46"); //Real Media
        FILE_TYPE_MAP.put("mpg", "000001BA"); //
        FILE_TYPE_MAP.put("mov", "6D6F6F76"); //Quicktime
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); //Windows Media
        FILE_TYPE_MAP.put("mid", "4D546864"); //MIDI (mid)
    }


    //统一初始化默认密码
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 设备状态
     */
    public static final String DEVICE_STATUS_UNUSED = "0";    //未使用
    public static final String DEVICE_STATUS_IN_USE = "1";    //使用中
    /**
     * 任务状态
     */
    public static final String TASK_STATUS_UNSTART = "0";       //未开始
    public static final String TASK_STATUS_DOING = "1";         //进行中
    public static final String TASK_STATUS_FINISHED = "2";      //已完成
    public static final String TASK_STATUS_CANCEL = "3";        //取消

    /**
     * gps设备状态
     */
    public static final String GPS_DEVICE_UNBANDING = "0"; //未绑定
    public static final String GPS_DEVICE_BANDING = "1";   //已绑定
}
