package com.example.nanjing.zy_java.manage;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class ChartManage {
    private BarChart barChart;
    private PieChart pieChart;
    private HorizontalBarChart horizontalBarChart;
    private XAxis xAxis;
    private YAxis left,right;

    public ChartManage(BarChart barChart) {
        this.barChart = barChart;
    }

    public ChartManage(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public ChartManage(HorizontalBarChart horizontalBarChart) {
        this.horizontalBarChart = horizontalBarChart;
    }
}
