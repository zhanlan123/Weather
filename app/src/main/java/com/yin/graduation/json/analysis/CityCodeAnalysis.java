package com.yin.graduation.json.analysis;

import com.yin.graduation.json.getdata.GetCityMess;

import org.json.JSONObject;

/**
 * Created by yin on 2016/4/4.
 */
public class CityCodeAnalysis {

    public static String getCityCode(String cityName){

        GetCityMess getCityMess = new GetCityMess(cityName);
        String cityData = getCityMess.request();

        String cityCode = "false";

        try {
            JSONObject jsonObject = new JSONObject(cityData);
            JSONObject personObject = jsonObject.getJSONObject("retData");
            cityCode = personObject.getString("cityCode");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cityCode;
    }
}
