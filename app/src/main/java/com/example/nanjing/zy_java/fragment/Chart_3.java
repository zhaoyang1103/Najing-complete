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
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Map;

public class Chart_3 extends Fragment {
    private TextView textview;
    private HorizontalBarChart horizontal_chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horichart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        horizontal_chart = (HorizontalBarChart) view.findViewById(R.id.horizontal_chart);
        ChartManage chartManage = new ChartManage(horizontal_chart);
        Map map = CarData.getMap();
        chartManage.showHorBarChart((ArrayList<String>) map.get("x3"), (ArrayList<Float>) map.get("y3"));
    }
}
