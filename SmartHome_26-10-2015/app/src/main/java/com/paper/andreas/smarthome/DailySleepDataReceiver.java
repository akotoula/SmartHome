package com.paper.andreas.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DailySleepDataReceiver extends BroadcastReceiver {
    public DailySleepDataReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean dataAvailable = intent.getBooleanExtra("dataAvailable",false);
        if (dataAvailable) {
            Intent startIntent = new Intent(context, SleepChartActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startIntent);
        }
        else {
            Toast.makeText(context,"No Data Available",Toast.LENGTH_LONG).show();
        }

    }
}
