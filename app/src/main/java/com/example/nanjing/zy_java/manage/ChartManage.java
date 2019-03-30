package com.example.nanjing.zy_java.manage;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class ChartManage {
    private BarChart barChart;
    private PieChart pieChart;
    private HorizontalBarChart horizontalBarChart;
    private XAxis xAxis;
    private YAxis left, right;


    public ChartManage(PieChart pieChart) {
        this.pieChart = pieChart;
        pieChart.setDescription("");
        pieChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(false);

    }


    public ChartManage(BarChart barChart) {
        this.barChart = barChart;
        xAxis = barChart.getXAxis();
        left = barChart.getAxisLeft();
        right = barChart.getAxisRight();
        barChart.setDescription("");


    }

    public ChartManage(HorizontalBarChart horizontalBarChart) {
        this.horizontalBarChart = horizontalBarChart;
        xAxis = barChart.getXAxis();
        left = barChart.getAxisLeft();
        right = barChart.getAxisRight();
        horizontalBarChart.setDescription("");
        left.setEnabled(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

    }

    public void showPiechart(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        PieData pieData = new PieData(x, pieDataSet);
        int[] colors = new int[]{Color.BLUE, Color.RED};
        pieDataSet.setColors(colors);
        pieDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##0.0")));
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    public void showHorBarChart(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(y.get(i), i));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "");
        BarData barData = new BarData(x, barDataSet);
        int[] colors = new int[]{Color.BLUE, Color.BLUE, Color.RED};
        barDataSet.setColors(colors);
        horizontalBarChart.setData(barData);
        barDataSet.setBarSpacePercent(50);
        barDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##0.0")));
        horizontalBarChart.invalidate();


    }

}
