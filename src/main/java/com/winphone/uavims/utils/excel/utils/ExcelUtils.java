/**
 * FileName:ExcelUtils
 * Author:lwl
 * Date:2018/1/2 14:12
 * Description:
 */
package com.winphone.xjwrj.utils.excel.utils;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    /**
     * 导出
     * @param in  模板文件
     * @param key  key值
     * @param contents  自定义内容
     */
    public static Workbook exportExcel(InputStream in,String key, List<Map<String, Object>> contents){
        Workbook workbook = null;
        //导出
        XLSTransformer transformer = new XLSTransformer();
        //
        Map<String,List> map = new HashMap();
        map.put(key,contents);
        try {
            workbook = transformer.transformXLS(in,map);
        } catch (Exception e) {
            throw new RuntimeException("导出excel文件失败",e);
        }
        return workbook;
    }
}
