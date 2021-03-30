package com.winphone.xjwrj.utils.excel.test;


import com.winphone.xjwrj.utils.excel.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/11
 * @Modified By:
 */

public class ExcelUtilTest {

    @Test
    public void showExcel() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("I://destFileName.xls")));
        HSSFSheet sheet = null;
        System.out.println("---workbook.getNumberOfSheets():" + workbook.getNumberOfSheets());
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            //获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            System.out.println("---sheet.getPhysicalNumberOfRows():" + sheet.getPhysicalNumberOfRows());
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                //获取每行
                HSSFRow row = sheet.getRow(j);
                if (row != null) {
                    System.out.println("---row.getPhysicalNumberOfCells():" + row.getPhysicalNumberOfCells());
                    for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                        //获取每个单元格
                        if (row.getCell(k) != null) {
                            System.out.print(row.getCell(k) + "\t");
                        }
                    }
                    System.out.println("---Sheet表" + i + "处理完毕---");
                }
            }
        }
    }

    @Test
    public void testRead() {
        try {

            ExcelUtil eu = new ExcelUtil();
            eu.setExcelPath("I://destFileName.xls");

            System.out.println("=======测试Excel 默认 读取========");
            eu.readExcel();

            System.out.println("\n=======测试Excel 从第四行读取，倒数第二行结束========");
            eu = eu.RestoreSettings();//还原设定
            eu.setStartReadPos(3);
            eu.setEndReadPos(-1);
            eu.readExcel();

            System.out.println("\n=======测试Excel 读取第二个sheet========");
            eu = eu.RestoreSettings();//还原设定
            eu.setSelectedSheetIdx(1);
            eu.readExcel();

            System.out.println("\n=======测试Excel 读取所有的sheet========");
            eu = eu.RestoreSettings();//还原设定
            eu.setOnlyReadOneSheet(false);
            eu.readExcel();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * excelUtil 测试类
     *
     * @throws IOException
     */
    @Test
    public void testOut() throws IOException {
        ExcelUtil eu = new ExcelUtil();

        InputStream inputStream = new FileInputStream(new File("I://destFileName.xls"));

        eu.setExcelPath("I://destFileName.xls");
        eu = eu.RestoreSettings();//还原设定
        eu.setOnlyReadOneSheet(false); // 查询所有list
        eu.setSelectedSheetIdx(0);//第一个sheet

        try {
            System.out.println("sheetCount:" + eu.getSheetSum()); //只获取数量
            List<Row> rows = eu.getRowIndex(1);
            List<Map<String, Object>> lists = eu.getData(rows);

            System.out.println("sheetName:" + eu.getSheetName(1));
            System.out.println("sheetCount:" + eu.getSheetCount()); //先解析sheet 获取数量
            System.out.println(lists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excelUtil 测试类
     *
     * @throws IOException
     */
    @Test
    public void testInputStream() throws IOException {
        ExcelUtil eu = new ExcelUtil();
        String fileName = "destFileName.xls";
        InputStream inputStream = new FileInputStream(new File("I://destFileName.xls"));

        eu = eu.RestoreSettings();//还原设定
        eu.setInputStream(inputStream);
        eu.setOnlyReadOneSheet(false); // 查询所有list
        eu.setSelectedSheetIdx(0);//第一个sheet

        try {
            System.out.println("sheetCount:" + eu.getSheetSum(inputStream, fileName)); //只获取数量
            List<Row> rows = eu.readExcelByIndex(inputStream, fileName, 1);
            List<Map<String, Object>> lists = eu.getData(rows);

            System.out.println("sheetName:" + eu.getSheetName(inputStream, fileName, 1));
//            System.out.println("sheetCount:"+eu.getSheetCount()); //先解析sheet 获取数量
            System.out.println(lists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
