package com.winphone.xjwrj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.lang.StringUtils;


public class LatitudeUtils {

    private static String KEY = "b0ea126edd9ea7edee66f79066649d4b";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");



    public static double[] addressToGPS(String address) {
        try {

            String url = String .format("http://restapi.amap.com/v3/geocode/geo?&s=rsv3&address=%s&key=%s", address, KEY);
            URL myURL = null;
            URLConnection httpsConn = null;
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStreamReader insr = null;
            BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = "";
                String line = null;
                while((line= br.readLine())!=null){
                    data+=line;
                }
                Matcher matcher = pattern.matcher(data);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    return gps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        double b = Math.floor(Math.random()*1000)/100000;
        System.out.println(b);
        double [] data = LatitudeUtils.addressToGPS("北京");
        System.out.println("经度:"+String.format("%.5f",data[0] + b) );
        System.out.println("纬度:"+String.format("%.5f",data[1] + b));
    }


}