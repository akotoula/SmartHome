package com.paper.andreas.smarthome;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;


public class SmartPlugDetailsActivity extends ActionBarActivity {

    private TextView mApplianceTextView;
    private TextView mLocationTextView;
    private Switch mConnectionSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_plug_details);
        Intent intent = getIntent();
        String plug_id = intent.getStringExtra("plug_id");
        String plug_appliance = intent.getStringExtra("plug_appliance");
        String plug_location = intent.getStringExtra("plug_location");
        boolean plug_connection = intent.getBooleanExtra("plug_connection", false);

        mApplianceTextView = (TextView) findViewById(R.id.appliance);
        mApplianceTextView.setText(plug_appliance);

        mLocationTextView = (TextView) findViewById(R.id.location);
        mLocationTextView.setText(plug_location);

        setTitle(plug_id);

        mConnectionSwitch = (Switch) findViewById(R.id.switch1);
        mConnectionSwitch.setChecked(plug_connection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smart_plug_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onShowConsumption(View v) {
        Intent startIntent = new Intent(this, DiagramActivity.class);
        startActivity(startIntent);
    }
}
