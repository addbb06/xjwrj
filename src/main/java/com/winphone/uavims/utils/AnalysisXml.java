package com.winphone.xjwrj.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/22
 * @Modified By:
 */

public class AnalysisXml {

    public static void main(String args[]) throws DocumentException {
        String webServiceUrl = "http://60.28.54.103:18080/cinterface/cqinterface.asmx/";

        AnalysisXml xml = new AnalysisXml();

//        xml.getAllBulid(webServiceUrl);

        xml.getEnergyByBuild(webServiceUrl);

    }

    /**
     * 获取所有的建筑
     * @param webServiceUrl
     * @return
     * @throws DocumentException
     */
    public List<String> getAllBulid(String webServiceUrl) throws DocumentException {
        List<String> buildingList = new ArrayList<>();
        String service_Method_getAllBuilding = "GetAllBuilding";

        //访问远程接口
        String url = webServiceUrl + service_Method_getAllBuilding;
        String result = getHttp(url);
        Document document = DocumentHelper.parseText(result);
        // 查询根节点
        Element root = document.getRootElement();

        List<Element> e = root.elements("string");

        for (Element ee:e) {
            buildingList.add(ee.getTextTrim());
        }
        return buildingList;
    }

    /**
     * 获取所有的建筑
     * @param webServiceUrl
     * @return
     * @throws DocumentException
     */
    public List<String> getEnergyByBuild(String webServiceUrl) throws DocumentException {
        List<String> buildingList = new ArrayList<>();

        String service_Method_getAllBuilding = "GetEnergyByBuild"+"?start=";

        String startDate = "2017-12-17 16:05:00";

        String endDate = "2017-12-23 17:05:00";

        //访问远程接口
        String url = webServiceUrl + service_Method_getAllBuilding;
        String result = getHttp(url);
        Document document = DocumentHelper.parseText(result);
        // 查询根节点
        Element root = document.getRootElement();

        String strings = root.getTextTrim();


        return buildingList;
    }



    /**
     * 发送http请求 并得到响应数据流
     *
     * @param url
     * @return data String
     */

    public static String getHttp(String url) {
        try {
            URL server = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) server.openConnection();
            httpConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            httpConnection.setRequestProperty("Accept", "application/x-www-form-urlencoded");
            httpConnection.setRequestProperty("version", "100");

            httpConnection.setConnectTimeout(120000);
            httpConnection.setReadTimeout(120000);
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);

            byte[] msgBody = null;
            DataInputStream dis = new DataInputStream(httpConnection.getInputStream());
            int length = httpConnection.getContentLength();
            //正常设置了Content-Length的值

            if (length >= 0) {
                msgBody = new byte[length];
                dis.readFully(msgBody);
            }
            // 未设置值

            else {
                byte[] temp = new byte[1024];
                int n = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((n = dis.read(temp)) != -1) {
                    bos.write(temp, 0, n);
                }
                msgBody = bos.toByteArray();
                bos.close();
            }
            dis.close();
            String data = new String(msgBody, "UTF-8").trim();

            httpConnection.disconnect();
            return data;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
