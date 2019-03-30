package com.example.nanjing.zy_java.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nanjing.zy_java.bean.ThresholdBean;
import com.example.nanjing.zy_java.util.Threshold;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class Dao {
    private Myhelper myhelper;

    public Dao(Context context) {
        myhelper = new Myhelper(context);

    }

    public long addIndex(ThresholdBean bean) {
        long lo = 0;
        SQLiteDatabase sqLiteDatabase = myhelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("temperature", bean.getTemperature());
        contentValues.put("humidity", bean.getTemperature());
        contentValues.put("light", bean.getTemperature());
        contentValues.put("co", bean.getTemperature());
        contentValues.put("pm", bean.getTemperature());
        contentValues.put("road", bean.getTemperature());
        lo = sqLiteDatabase.insert("indextable", null, contentValues);
        sqLiteDatabase.close();
        return lo;
    }

    public List<ThresholdBean> queryall() {
        List<ThresholdBean> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("indextable", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ThresholdBean thresholdBean = new ThresholdBean();
            thresholdBean.setTemperature(cursor.getInt(1));
            thresholdBean.setHumidity(cursor.getInt(2));
            thresholdBean.setLightIntensity(cursor.getInt(3));
            thresholdBean.setCo2(cursor.getInt(4));
            thresholdBean.set_$Pm2526(cursor.getInt(5));
            thresholdBean.setRoad(cursor.getInt(6));
            list.add(thresholdBean);
        }
        List<ThresholdBean> read_list = new ArrayList<>();
        if (list.size() > 20) {
            for (int i = list.size() - 20; i < list.size(); i++) {
                read_list.add(list.get(i));
            }


        } else {
            for (int i = 0; i < list.size(); i++) {
                read_list.add(list.get(i));

            }
        }

        return read_list;
    }


}
