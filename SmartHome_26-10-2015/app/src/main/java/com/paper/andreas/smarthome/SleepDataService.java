package com.paper.andreas.smarthome;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SleepDataService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GET_DAILY_SLEEP_DATA = "com.paper.andreas.smarthome.action.ACTION_GET_DAILY_SLEEP_DATA";


    private static final String EXTRA_DATE = "com.paper.andreas.smarthome.extra.date";
   // private static final String EXTRA_PARAM2 = "com.paper.andreas.smarthome.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionGetDailySleepData(Context context, String date) {
        Intent intent = new Intent(context, SleepDataService.class);
        intent.setAction(ACTION_GET_DAILY_SLEEP_DATA);
        intent.putExtra(EXTRA_DATE, date);
        context.startService(intent);
    }


    public SleepDataService() {
        super("SleepDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_DAILY_SLEEP_DATA.equals(action)) {
                final String date = intent.getStringExtra(EXTRA_DATE);
                handleActionGetDailySleepData(date);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetDailySleepData(String date) {

        SleepDataController sleepDataController = new SleepDataController(this);

        ArrayList<SleepDataRecord> data = sleepDataController.findDailySleepDataInDatabase(date);

        if (data == null) { //no data available
            //show dialog fragment to user
           // FailureDialogFragment dialogFragment = new FailureDialogFragment();
           // dialogFragment.show(getFragmentManager(), "errordialog");
            Intent intent = new Intent("com.paper.andreas.smarthome.sleepdata");
            intent.putExtra("dataAvailable", false);
            sendBroadcast(intent);
        }
        else {
            //show SleepChartActivity with data
            App.instance().setmDailySleepRecords(data);

           // Intent startIntent = new Intent(this, SleepChartActivity.class);
           // startActivity(startIntent);

            Intent intent = new Intent("com.paper.andreas.smarthome.sleepdata");
            intent.putExtra("dataAvailable", true);
            sendBroadcast(intent);
        }

        Log.i("","");
    }


}
