package com.example.nanjing.zy_java.util;

import com.example.nanjing.zy_java.bean.ThresholdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class IndexListSave {
    public static List<ThresholdBean> list_save = new ArrayList<>();

    public static List<ThresholdBean> getList_save() {
        return list_save;
    }

    public static void setList_save(ThresholdBean bean) {
        if (list_save.size() > 60) {
            for (int i = 0; i < 30; i++) {
                list_save.remove(i);
            }

        }
        list_save.add(bean);
    }
}
