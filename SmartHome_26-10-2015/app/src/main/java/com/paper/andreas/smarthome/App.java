package com.paper.andreas.smarthome;

import java.util.ArrayList;

/**
 * Created by Adreas on 18/10/2015.
 */
public class App {

    private App() {}

    private static App sInstance;

    private ArrayList<SleepDataRecord> mDailySleepRecords;
    private String mDateSelected;

    public String getmDateSelected() {
        return mDateSelected;
    }

    public void setmDateSelected(String mDateSelected) {
        this.mDateSelected = mDateSelected;
    }

    public static App instance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    public ArrayList<SleepDataRecord> getmDailySleepRecords() {
        return mDailySleepRecords;
    }

    public void setmDailySleepRecords( ArrayList<SleepDataRecord> records) {
        mDailySleepRecords = records;
    }
}
