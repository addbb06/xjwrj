package com.winphone.xjwrj.utils.excel.test;

import com.winphone.xjwrj.utils.excel.entity.TestEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author: zhou
 * @Description: 导入测试文件类
 * @Date:Create in 2017/10/25
 * @Modified By:
 * @See http://www.afterturn.cn/doc/easypoi.html
 */

public class ExcelTest {


    @Test
    public void test2() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        long start = System.currentTimeMillis();
        // 读取文件
        List<TestEntity> list_file = ExcelImportUtil.importExcel(
                new File(("i:\\test.xlsx")),
                TestEntity.class, params);

        //文件流
        File file = new File("i:\\test.xlsx");
        InputStream is = new FileInputStream(file);

        List<TestEntity> list = ExcelImportUtil.importExcel(is, TestEntity.class, params);

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(ReflectionToStringBuilder.toString(list.get(i)));
        }

    }
}
