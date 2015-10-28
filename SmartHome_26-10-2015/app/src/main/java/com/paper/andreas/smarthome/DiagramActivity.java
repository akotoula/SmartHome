package com.paper.andreas.smarthome;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Random;


public class DiagramActivity extends Activity {

    private LineChart mConsumptionChart;

    private TextView mDailyConsumption;
    private TextView mDailyCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);

        mConsumptionChart = (LineChart) findViewById(R.id.consumptionchart);

        mDailyConsumption = (TextView) findViewById(R.id.dailyconsumption);
        mDailyCost = (TextView) findViewById(R.id.dailycost);



        YAxis leftAxis = mConsumptionChart.getAxisLeft();
        LimitLine ll = new LimitLine(140f, "Consumption (KW)");
        ll.setLineColor(Color.RED);
        ll.setLineWidth(4f);
        ll.setTextColor(Color.BLACK);
        ll.setTextSize(12f);
        leftAxis.addLimitLine(ll);
       // leftAxis.setDrawLabels(true);

        XAxis xAxis = mConsumptionChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        //xAxis.setDrawLabels(true);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();

        float minX = 0.0f;
        float maxX = 5.0f;

        Random rand = new Random();

       // float finalX = rand.nextFloat() * (maxX - minX) + minX;

        for (int i = 0; i<24; i++) {
            float finalX = rand.nextFloat() * (maxX - minX) + minX;
            Entry c1e1 = new Entry(finalX, i);
            valsComp1.add(c1e1);
        }


        LineDataSet setComp1 = new LineDataSet(valsComp1, "KW");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("00:00");
        xVals.add("01:00");
        xVals.add("02:00");
        xVals.add("03:00");
        xVals.add("04:00");
        xVals.add("05:00");
        xVals.add("06:00");
        xVals.add("07:00");
        xVals.add("08:00");
        xVals.add("09:00");
        xVals.add("10:00");
        xVals.add("11:00");
        xVals.add("12:00");
        xVals.add("13:00");
        xVals.add("14:00");
        xVals.add("15:00");
        xVals.add("16:00");
        xVals.add("17:00");
        xVals.add("18:00");
        xVals.add("19:00");
        xVals.add("20:00");
        xVals.add("21:00");
        xVals.add("22:00");
        xVals.add("23:00");


        LineData data = new LineData(xVals, dataSets);
        mConsumptionChart.setData(data);
        mConsumptionChart.invalidate(); // refresh

        float minX2 = 3.0f;
        float maxX2 = 6.0f;
        float kwh = rand.nextFloat() * (maxX - minX) + minX;

        mDailyConsumption.setText(Float.toString(kwh));

        float cost = kwh * SmartPlugsListActivity.getKwhPrice(this);
        mDailyCost.setText(Float.toString(cost));
    }




}
