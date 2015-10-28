package com.paper.andreas.smarthome;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SleepChartActivity extends AppCompatActivity {
    /*
    aDreas
    * */

    private PieChart mChart;

    private DonutProgress mProgress;

    private String[] xData = { "Awake", "Light Sleep", "Deep Sleep"};

    private int[] yData;

    //private TextView mDateTexView;

    private TextView mAwake;
    private TextView mLightSleep;
    private TextView mDeepSleep;
    private TextView mInBed;
    private TextView mTotalSleep;
    private TextView mWokeuptimesTextView;

    private ArrayList<SleepDataRecord> mDailySleepRecords;


    private int mAwakeMins = 0;
    private int mTotalSleepMins= 0;
    private int mLightSleepMins= 0;
    private int mDeepSleepMins= 0;
    private int mWokeUpTimes= 0;


   // private int awakemins;
    //private int lightSleepMins;
    //private int deepSleepMins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_chart);

        mDailySleepRecords = App.instance().getmDailySleepRecords();
        if (mDailySleepRecords != null) {
            calculateSleepData();
        }

        mProgress = (DonutProgress) findViewById(R.id.donut_progress);

       // mDateTexView = (TextView) findViewById(R.id.currentdate);


        /*
        String strCurrentDate = App.instance().getmDateSelected();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
            format = new SimpleDateFormat("EEE, dd MMM yyyy");
            String date = format.format(newDate);
            mDateTexView.setText(date);
        } catch (ParseException e) {
            mDateTexView.setText(strCurrentDate);
        }
        */



        mAwake = (TextView) findViewById(R.id.awake);
        mLightSleep = (TextView) findViewById(R.id.lightsleep);
        mDeepSleep = (TextView) findViewById(R.id.deepsleep);
        mInBed = (TextView) findViewById(R.id.inbed);
        mTotalSleep = (TextView) findViewById(R.id.totalsleep);
        mWokeuptimesTextView = (TextView) findViewById(R.id.wokeuptimes);

        mChart = (PieChart)findViewById(R.id.sleepchart);
        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Daily Sleep Data");

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

        mAwake.setText(Integer.toString(mAwakeMins) + " min");
        mLightSleep.setText(Integer.toString(mLightSleepMins) + " min");
        mDeepSleep.setText(Integer.toString(mDeepSleepMins) + " min");
        mInBed.setText(Integer.toString(mAwakeMins + mDeepSleepMins + mLightSleepMins) + " min");
        mTotalSleep.setText(Integer.toString(mDeepSleepMins + mLightSleepMins) + " min");
        mWokeuptimesTextView.setText(Integer.toString(mWokeUpTimes) + " times");


        int progress = 100;
        if (mTotalSleepMins <= 480) {
            progress = (mTotalSleepMins*100)/480;
        }

        mProgress.setProgress(progress);

    }


    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yData = getSleepData();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Sleep Data");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }


    private int[] getSleepData() {
        //int daymins = 1440;

       // Random rand = new Random();
       // int awake = rand.nextInt(1200 - 960)  + 960;


       // int sleep = daymins - awake;
       // int deepSleep = rand.nextInt(sleep - sleep/2) + sleep/2;
       // int lightSleep = sleep - deepSleep;
        int[] yData = {mAwakeMins,mLightSleepMins,mDeepSleepMins};
        return yData;
    }


    private void calculateSleepData() {

        for (SleepDataRecord record: mDailySleepRecords) {
            String type = record.getmType0();
            String duration = record.getmDuration();
            int durationSeconds = 0;
            try {
                 durationSeconds = Integer.parseInt(duration);
            }
            catch (Exception e){
                continue;
            }


            if (type.equalsIgnoreCase("Awake")) {
                mWokeUpTimes++;
                mAwakeMins = mAwakeMins + durationSeconds/60;
            }
            else if (type.equalsIgnoreCase("LightSleep")) {
                mLightSleepMins = mLightSleepMins + durationSeconds/60;
            }
            else if (type.equalsIgnoreCase("DeepSleep")) {
                mDeepSleepMins = mDeepSleepMins + durationSeconds/60;
            }
        }

        mTotalSleepMins = mLightSleepMins + mDeepSleepMins;

    }

}
