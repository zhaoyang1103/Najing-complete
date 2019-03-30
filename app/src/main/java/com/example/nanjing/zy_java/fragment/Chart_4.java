package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.zy_java.data.CarData;
import com.example.nanjing.zy_java.manage.ChartManage;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.Map;

public class Chart_4 extends Fragment {
    private TextView textview;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.barchart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        barChart = (BarChart) view.findViewById(R.id.barchart);
        ChartManage chartManage = new ChartManage(barChart);
        Map map = CarData.getMap();
        chartManage.showDoubleBarChart((ArrayList<String>) map.get("x4"), (ArrayList<float[]>) map.get("y4"));
    }
}
