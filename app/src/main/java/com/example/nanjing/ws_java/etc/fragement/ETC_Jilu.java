package com.example.nanjing.ws_java.etc.fragement;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.ws_java.etc.adapter.JiluAdapter;
import com.example.nanjing.ws_java.etc.adapter.YueAdapter;
import com.example.nanjing.ws_java.etc.bean.JiluBean;
import com.example.nanjing.ws_java.etc.dao.JiluDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ETC_Jilu extends Fragment {
    private TextView j_zong;
    private ListView listview3;
    private TextView tishi;
    private JiluAdapter adapter;
    private List<JiluBean> list;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.etc_jilu, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        list = new JiluDao(context).query();
        Collections.sort(list, new Comparator<JiluBean>() {
            @Override
            public int compare(JiluBean o1, JiluBean o2) {
                return o2.getId() - o1.getId();
            }
        });
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getBalance();
        }
        j_zong.setText("充值合计："+sum+"元");
        adapter = new JiluAdapter(context,list);
        listview3.setAdapter(adapter);
        listview3.setEmptyView(tishi);
        return view;
    }

    private void initView(View view) {
        j_zong = (TextView) view.findViewById(R.id.j_zong);
        listview3 = (ListView) view.findViewById(R.id.listview3);
        tishi = (TextView) view.findViewById(R.id.tishi);
    }
}
