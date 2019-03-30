package com.example.nanjing.zy_java.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class Myhelper  extends SQLiteOpenHelper {
    public Myhelper( Context context) {
        super(context, "index.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table indextable (id integer primary key autoincrement," +
                "temperature int," +
                "humidity int ," +
                "light int," +
                "co int," +
                "pm int," +
                "road int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("升级");
    }
}
