package com.yin.graduation.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yin.graduation.wather.plugin.bean.RealTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yin on 2016/3/4.
 */
public class MyOperator {

    private static final String TABLE_NAME = "todaywether";//要操作的数据表的名称
    private SQLiteDatabase db = null; //数据库操作

    public MyOperator(SQLiteDatabase db){
        this.db = db;
    }

    //插入操作
    public void insert(RealTime realTime){
        String sql = "INSERT INTO " + TABLE_NAME + "(cityCode,humidity,temp," +
                "weather_name,wind) values(?,?,?,?,?)";
        Object args[] = new Object[]{realTime.getCity_code(),realTime.getHumidity(),
                                realTime.getTemp(),realTime.getWeather_name(),
                                realTime.getWind()};
        this.db.execSQL(sql,args);
    }

    //更新操作
    public void update(RealTime realTime){
        String sql = "UPDATE " + TABLE_NAME + " SET humidity=?,temp=?,weather_name=?,wind=? WHERE cityCode=?";
        Object args[]=new Object[]{realTime.getHumidity(),realTime.getTemp(),
                realTime.getWeather_name(),realTime.getWind(),
                                realTime.getCity_code()};
        this.db.execSQL(sql, args);
    }

    //查询
    public RealTime getstatebyId(String cityCode){
        RealTime realTime = new RealTime();

        List<String> all = new ArrayList<String>(); //此时只是String
        String sql = "SELECT * FROM " + TABLE_NAME + " where cityCode=?";
        String args[] = new String[]{String.valueOf(cityCode)};
        Cursor result = this.db.rawQuery(sql, args);

        for(result.moveToFirst();!result.isAfterLast();result.moveToNext()  )
        {
            realTime.setCity_code(result.getString(1));
            realTime.setHumidity(result.getInt(2));
            realTime.setTemp(result.getInt(3));
            realTime.setWeather_name(result.getString(4));
            realTime.setWind(result.getString(5));
        }
        return realTime;
    }

    //判断插入数据的ID是否已经存在数据库中。
    public boolean check_same(String cityCode)
    {
        String sql="SELECT cityCode from " + TABLE_NAME + " where cityCode = ?";
        String args[] =new String[]{String.valueOf(cityCode)};
        Cursor result=this.db.rawQuery(sql,args);
        Log.e("database", "the sql has been excuate");

        Log.e("database","the hang count" + String.valueOf(result.getCount()));

        if(result.getCount()==0)//判断得到的返回数据是否为空
        {
            Log.e("database", "return false and not exist the same result" + String.valueOf(result.getCount()));
            return false;
        }
        else
        {
            Log.e("database", "return true and exist the same result"+ String.valueOf(result.getCount()));
            return true;
        }
    }

    public String findLocation(){

        String sql = "SELECT cityCode from location_city where id = ?";
        String args[] =new String[]{String.valueOf(1)};
        Cursor result = this.db.rawQuery(sql,args);
        result.moveToFirst();
        String cityCode = result.getString(0);
        return cityCode;
    }

    public void locationInsert(String cityCode, String cityName){
        String sql = "INSERT INTO location_city(cityName,cityCode) values(?,?)";
        Object args[] = new Object[]{cityName,cityCode};
        this.db.execSQL(sql,args);
        this.db.close();

    }

    public boolean check_location(){
        String sql = "SELECT * FROM location_city where id = ?";
        String args[] =new String[]{String.valueOf(1)};
        Cursor result = this.db.rawQuery(sql,args);
        if(result.getCount() == 0){
            return false;
        }else{
            return true;
        }
    }
}
