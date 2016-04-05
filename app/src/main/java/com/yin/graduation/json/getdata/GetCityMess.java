package com.yin.graduation.json.getdata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yin on 2016/4/4.
 */
public class GetCityMess {


    private String httpUrl = "http://apis.baidu.com/apistore/weatherservice/cityinfo";
    private String httpArg;

    public GetCityMess(String city_iso){
        byte[] t_iso = new byte[0];
        try {
            t_iso = city_iso.getBytes("ISO8859-1");
            String ut_iso = new String(t_iso, "ISO8859-1");
            httpArg = "cityname="+city_iso;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    /**
     * @return 返回结果
     */
    public String request() {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "fc7dbd563d0e2d47253e922c312e0125");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
