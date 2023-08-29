package com.example.dbandeng.utils;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetDataChart {
    BarChart chart;
    List<BarEntry> entries = new ArrayList<>();
    public SetDataChart(BarChart chart){
        this.chart = chart;
    }

    public void useDumpDataChart(){
        for(int i = 0; i < 10; i ++){
            entries.add(new BarEntry(i,generateRandomInt()));
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        chart.setData(data);
    }

    public int generateRandomInt(){
        int min = 10;
        int max = 50;

        Random random = new Random();
        int randomValue = random.nextInt(max - min + 1) + min;

        return randomValue;
    }
}
