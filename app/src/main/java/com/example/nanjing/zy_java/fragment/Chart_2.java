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
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Map;

public class Chart_2 extends Fragment {
    private TextView textview;
    private PieChart piechart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.piechart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        piechart = (PieChart) view.findViewById(R.id.piechart);
        ChartManage chartManage = new ChartManage(piechart);
        Map map = CarData.getMap();
        chartManage.showPiechart((ArrayList<String>) map.get("x2"), (ArrayList<Float>) map.get("y2"));
    }
}
