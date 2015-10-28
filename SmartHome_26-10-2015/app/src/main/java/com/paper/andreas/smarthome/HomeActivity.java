package com.paper.andreas.smarthome;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends ActionBarActivity {

    //this is feature 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }




    public void onPowerAndConsumption(View v) {
        Intent startIntent = new Intent(this, SmartPlugsListActivity.class);
        startActivity(startIntent);

    }

    public void onHealthAndFitness(View v) {
        Intent startIntent = new Intent(this, HealthAndFitnessHomeActivity.class);
        startActivity(startIntent);

    }
}
