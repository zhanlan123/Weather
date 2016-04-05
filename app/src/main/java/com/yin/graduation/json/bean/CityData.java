package com.yin.graduation.json.bean;

/**
 * Created by yin on 2016/4/4.
 */
public class CityData {

    //城市名称
    private String cityName;
    //所属省份
    private String provinceName;
    //天气预报城市代码
    private String cityCode;
    //邮编
    private String zipCode;
    //电话区号
    private String telAreaCode;

    public CityData(String telAreaCode, String cityName, String provinceName, String cityCode, String zipCode) {
        this.telAreaCode = telAreaCode;
        this.cityName = cityName;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelAreaCode() {
        return telAreaCode;
    }

    public void setTelAreaCode(String telAreaCode) {
        this.telAreaCode = telAreaCode;
    }
}
