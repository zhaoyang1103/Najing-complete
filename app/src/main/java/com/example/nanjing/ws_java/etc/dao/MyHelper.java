package com.example.nanjing.ws_java.etc.dao;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nanjing.ws_java.etc.bean.JiluBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MyHelper extends OrmLiteSqliteOpenHelper {
    public MyHelper(Context context) {
        super(context, "nanjing.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, JiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, JiluBean.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MyHelper myHelper = null;

    public static synchronized MyHelper getHelper(Context context) {
        if (myHelper == null) {
            synchronized (MyHelper.class) {
                if (myHelper == null) {
                    myHelper = new MyHelper(context);
                }
            }
        }
        return myHelper;
    }

    private Map<String, Dao> daos = new HashMap<>();

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String simpleName = clazz.getSimpleName();
        if (daos.containsKey(simpleName)) {
            dao = daos.get(simpleName);
        }
        if (dao == null){
            dao = super.getDao(clazz);
            daos.put(simpleName,dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao =null;
        }
    }
}
