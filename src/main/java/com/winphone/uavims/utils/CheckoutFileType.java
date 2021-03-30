package com.winphone.xjwrj.utils;

import com.winphone.xjwrj.common.Constant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class CheckoutFileType {

    public static boolean getUpFileLegitimacyFlag(MultipartFile file){
        //根据指定文件的文件头判断其文件类型
        try {
            String type = getFileType(file);
            if(type !=null){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     *根据指定文件的文件头判断其文件类型
     * @param file
     * @return
     */
    public static String getFileType(MultipartFile file) {
        String res = null;
        InputStream is;
        try {
            is = file.getInputStream();
            byte[] b = new byte[16];
            is.read(b,0, b.length);
            String filetypeHex = String.valueOf(bytesToHexString(b));
            Iterator<Map.Entry<String, String>> entryiterator = Constant.FILE_TYPE_MAP.entrySet().iterator();
            while (entryiterator.hasNext()) {
                Map.Entry<String,String> entry = entryiterator.next();
                String fileTypeHexValue = entry.getValue();
                if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                    res = entry.getKey();
                    if(res.equals("office")) {
                        res = getOfficeFileType(is);
                    }
                    is.close();
                    break;
                }
            }

            // 如果不是上述类型，则判断扩展名
            if(res == null){
                String fileName = file.getName();
                // 如果无扩展名，则直接返回空串
                if(-1 == fileName.indexOf(".")){
                    return "";
                }
                // 如果有扩展名，则返回扩展名
                return fileName.substring(fileName.indexOf(".") + 1);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 判断office文件的具体类型
     * @param is
     * @return
     */
    private static String getOfficeFileType(InputStream is){
        String officeFileType = "doc";
        byte[] b = new byte[512];
        try {
            is.read(b, 0, b.length);
            String filetypeHex = String.valueOf(bytesToHexString(b));
            String flagString = filetypeHex.substring(992, filetypeHex.length());
            if(flagString.toLowerCase().startsWith("eca5c")){
                officeFileType = "doc";
            } else if(flagString.toLowerCase().startsWith("fdffffff09")){
                officeFileType = "xls";

            } else if(flagString.toLowerCase().startsWith("09081000000")){
                officeFileType = "xls";
            } else {
                officeFileType = "ppt";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return officeFileType;
    }


    /**
     * 得到上传文件的文件头
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
