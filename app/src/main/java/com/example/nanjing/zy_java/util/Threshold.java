package com.example.nanjing.zy_java.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class Threshold {
    public static void saveSwitch(Context context,boolean isCheck){
        SharedPreferences sharedPreferences = context.getSharedPreferences("switch",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("flag",isCheck);
        editor.commit();
    }

    public static boolean getSwitch(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("switch",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("flag",false);
    }

    public static void saveYuZhi(Context context,int wendu,int shidu,int guangzhao,int co2,int pm25,int status){
        SharedPreferences sharedPreferences = context.getSharedPreferences("yuzhi",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("wendu",wendu);
        editor.putInt("shidu",shidu);
        editor.putInt("guangzhao",guangzhao);
        editor.putInt("co2",co2);
        editor.putInt("pm25",pm25);
        editor.putInt("status",status);
        editor.commit();
    }

    public static int getYuZhi(Context context,String msg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("yuzhi",Context.MODE_PRIVATE);
        if (msg.equals("wendu")){
            return sharedPreferences.getInt("wendu",32);
        }
        if (msg.equals("shidu")){
            return sharedPreferences.getInt("shidu",80);
        }
        if (msg.equals("guangzhao")){
            return sharedPreferences.getInt("guangzhao",567);
        }
        if (msg.equals("co2")){
            return sharedPreferences.getInt("co2",324);
        }
        if (msg.equals("pm25")){
            return sharedPreferences.getInt("pm25",254);
        }
        if (msg.equals("status")){
            return sharedPreferences.getInt("status",3);
        }
        return 0;
    }
}
