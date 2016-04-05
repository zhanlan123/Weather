package com.yin.graduation.json.bean;

/**
 * Created by yin on 2016/4/4.
 */
public class CityMess {

    private int errNum;
    private String retMsg;
    private CityData cityData;

    public CityMess(int errNum, String retMsg, CityData cityData) {
        this.errNum = errNum;
        this.retMsg = retMsg;
        this.cityData = cityData;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }
}
