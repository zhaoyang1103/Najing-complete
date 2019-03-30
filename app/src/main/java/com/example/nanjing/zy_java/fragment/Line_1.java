package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.zy_java.bean.ThresholdBean;
import com.example.nanjing.zy_java.dao.Dao;
import com.example.nanjing.zy_java.manage.ChartManage;
import com.example.nanjing.zy_java.util.IndexListSave;
import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Line_1 extends Fragment {
    private TextView textview;
    private LineChart linechart;
    private int position;
    private Timer timer;
    private List<Integer> list;
    private List<String> x_time;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            chartManage.showLineChart(x_time, list);
            super.handleMessage(msg);
        }
    };
    private ChartManage chartManage;
    private Dao dao;
    private List<ThresholdBean> tabl_lsit;

    public static Fragment getIntances(int a) {
        Line_1 line_1 = new Line_1();
        Bundle bundle = new Bundle();
        bundle.putInt("position", a);
        line_1.setArguments(bundle);
        return line_1;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linechart, container, false);
        position = getArguments().getInt("position");
        initView(view);
        return view;
    }


    private void initView(View view) {
        linechart = (LineChart) view.findViewById(R.id.linechart);
        timer = new Timer();
        list = new ArrayList<>();
        dao = new Dao(getContext());
        x_time = new ArrayList<>();

        chartManage = new ChartManage(linechart);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getData();

            }
        }, 0, 3000);


    }


    private void getData() {
        tabl_lsit = dao.queryall();
        list = new ArrayList<>();
        Log.i("y的数列", "getData: " + tabl_lsit.size());
        x_time = new ArrayList<>();
        if (position == 0) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).getTemperature());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        } else if (position == 1) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).getHumidity());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        } else if (position == 2) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).getLightIntensity());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        } else if (position == 3) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).getCo2());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        } else if (position == 4) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).get_$Pm2526());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        } else if (position == 5) {
            for (int i = 0; i < tabl_lsit.size(); i++) {
                list.add(tabl_lsit.get(i).getRoad());
                x_time.add(simpleDateFormat.format(System.currentTimeMillis()));
            }

        }
        handler.sendEmptyMessage(0);

    }
}
