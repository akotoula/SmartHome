package com.paper.andreas.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.Random;

public class StepsChartActivity extends AppCompatActivity {

    private TextView mStepsTextView;
    private DonutProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_chart);

        mStepsTextView = (TextView) findViewById(R.id.stepstextView);

        Random r = new Random();
       // int steps = r.nextInt(StepsFragment.getStepsGoal(this));
        int steps = r.nextInt(5000);

        mStepsTextView.setText(Integer.toString(steps));
        int progress= 0;

        try {
             progress = (steps*100)/StepsFragment.getStepsGoal(this);
        }
        catch (Exception e) {}

        if (steps >= StepsFragment.getStepsGoal(this)){
            progress = 100;
        }

        mProgress = (DonutProgress) findViewById(R.id.donut_progress);
        mProgress.setProgress(progress);

    }


}
