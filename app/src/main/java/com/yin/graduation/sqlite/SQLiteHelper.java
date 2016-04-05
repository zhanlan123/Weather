package com.yin.graduation.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yin on 2016/3/4.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="weather.db";
    private static final int  DATABASE_VERSION=1;//更改版本后数据库将重新创建
    private static final String TABLE_NAME="todaywether";

    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                +"id INTEGER PRIMARY KEY autoincrement,"
                +"cityCode VARCHAR(9),"
                +"humidity INTEGER,"
                +"temp INTEGER,"
                +"weather_name VARCHAR(10),"
                +"wind VARCHAR(25)"
                +")";
        db.execSQL(sql);

        String sql_2 = "CREATE TABLE city" + "("
                +"id INTEGER PRIMARY KEY autoincrement,"
                +"cityName varchar(25),"
                +"cityCode varchar(9)"
                +")";
        db.execSQL(sql_2);

        String sql_3 = "CREATE TABLE location_city" + "("
                +"id INTEGER PRIMARY KEY autoincrement,"
                +"cityName varchar(25),"
                +"cityCode varchar(9)"
                +")";
        db.execSQL(sql_3);

        Log.e("createLog","数据库创建完成");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        this.onCreate(db);
        Log.e("updatedb","数据库更新完成");
    }
}
