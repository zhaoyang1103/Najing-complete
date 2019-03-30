package com.example.nanjing.ws_java.etc.dao;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;

import com.example.nanjing.ws_java.etc.bean.JiluBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JiluDao {
    private Context context;
    private MyHelper myHelper;
    private Dao<JiluBean,Integer> dao;

    public JiluDao(Context context) {
        this.context = context;
        try {
            myHelper = MyHelper.getHelper(context);
            dao = myHelper.getDao(JiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(JiluBean bean){
        try {
            dao.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JiluBean> query(){
        List<JiluBean> list = new ArrayList<>();
        try {
            list = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
