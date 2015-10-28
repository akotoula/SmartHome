package com.paper.andreas.smarthome;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Adreas on 27/9/2015.
 */
public class HeartRateFragment extends android.support.v4.app.Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private TextView mHeartTextView;
    private int mLastHeartRate;

    Timer timer;

    TimerTask timerTask;

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HeartRateFragment newInstance(int sectionNumber) {
        HeartRateFragment fragment = new HeartRateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HeartRateFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heart, container, false);

        mHeartTextView = (TextView) rootView.findViewById(R.id.heartRatetextView);

        mLastHeartRate = 60;
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        startTimer();

    }

    @Override
    public void onPause() {
        super.onPause();
        stoptimertask();
    }


    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000m
        timer.schedule(timerTask, 2000, 2000); //
    }


    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        Random r = new Random();

                        int rate = r.nextInt(120-50) + 50;
                        while (Math.abs(rate - mLastHeartRate) > 20) {
                            rate = r.nextInt(120-50) + 50;
                        }
                        mHeartTextView.setText(Integer.toString(rate));

                        if (rate >= 90) {
                            showNotification("Your heart rate is too high! Try to relax.",getActivity(),1000);
                        }
                    }
                });
            }
        };
    }



    /**
     * Shows notification to user
     * @param text, the notifications text
     * @param ctx, the app's context
     * @param notificationID
     */
    @SuppressLint("NewApi")
    public static void showNotification(String text,Context ctx,int notificationID) {
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PendingIntent notifyPIntent = PendingIntent.getActivity(ctx, 0, new Intent(), 0);
            Notification.Builder builder = new Notification.Builder(ctx);
            builder.setContentTitle("Attention")
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(notifyPIntent)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(notifyPIntent)
                    .setContentIntent(PendingIntent.getActivity(ctx, 0, new Intent(), 0))
                    .setAutoCancel(true) ;

            Notification notification = new Notification.BigTextStyle(builder).bigText(text).build();
            notificationManager.notify(notificationID, notification);
        }

    }

}
