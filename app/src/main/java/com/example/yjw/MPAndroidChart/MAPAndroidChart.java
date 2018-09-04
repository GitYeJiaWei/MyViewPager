package com.example.yjw.MPAndroidChart;

import android.graphics.Color;
import android.os.Bundle;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MAPAndroidChart extends BaseActivity {

    @BindView(R.id.bar_chart1)
    BarChart barChart1;
    @BindView(R.id.bar_chart2)
    BarChart barChart2;
    @BindView(R.id.bar_chart3)
    LineChart barChart3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapandroid_chart);
        ButterKnife.bind(this);

        initview();
    }

    private void initview() {
        BarChartManager barChartManager1 = new BarChartManager(barChart1);
        BarChartManager barChartManager2 = new BarChartManager(barChart2);

        barChart1.setTouchEnabled(false);


        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 7; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);


        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("今天");
        names.add("上月");


        //创建多条折线的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colours.get(0));
        barChartManager1.setDescription("");

        barChartManager2.showBarChart(xValues, yValues, names, colours);
        barChartManager2.setXAxis(7f, 0f, 7);
        barChartManager2.setDescription("");

    }
}
