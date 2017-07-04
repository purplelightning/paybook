package com.example.wind.paybook;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by wind on 17-7-4.
 */

public class ChartsActivity extends Activity {

    private LineChartView mChart;
    private Map<String,Integer> table=new TreeMap<>();
    private LineChartData mData;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);

        mChart= (LineChartView) findViewById(R.id.chart);
        mData=new LineChartData();
        List<CostBean> allDate= (List<CostBean>) getIntent().
                getSerializableExtra("cost_list");
        generateValues(allDate);
        generateData();

//        mChart=new LineChartView(ChartsActivity.this);

    }

    private void generateData() {
        List<Line> lines=new ArrayList<>();
        List<PointValue> values=new ArrayList<>();
        int indexX=0;
        for(Integer value:table.values()){
            values.add(new PointValue(indexX,value));
            indexX++;
        }

        Line line=new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLOR_BLUE);
        lines.add(line);

        mData.setLines(lines);
        mChart.setLineChartData(mData);
    }

    private void generateValues(List<CostBean> allDate) {

        if(allDate!=null){
            for (int i=0;i<allDate.size();i++){
                CostBean costBean=allDate.get(i);
                String costDate=costBean.getCostDate();
                //把String转化为Int
                int costMoney= Integer.parseInt(costBean.getCostMoney());
                if(!table.containsKey(costDate)){
                    table.put(costDate,costMoney);
                }else{
                    int originMoney=table.get(costDate);
                    table.put(costDate,originMoney+costMoney);
                }
            }
        }

    }
}







