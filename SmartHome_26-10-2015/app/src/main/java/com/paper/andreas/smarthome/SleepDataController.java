package com.paper.andreas.smarthome;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.paper.andreas.smarthome.SleepDataMappingContract.SleepDataEntry;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adreas on 17/10/2015.
 */
//this class should be initialized in different thread
public class SleepDataController {

    private SleepDataDbHelper mDbHelper;
    private SQLiteDatabase mDb;

    public SleepDataController(Context context) {
        mDbHelper = new SleepDataDbHelper(context);
    }

    private boolean initializeDatabase() {
        if (mDbHelper == null) {
            return false;
        }
        try {
            mDbHelper.createDataBase();
        }
        catch (IOException ioe) {
            //throw new Error("Unable to create database");
            return false;
        }
        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getReadableDatabase();
        }catch(SQLException sqle){
            return false;
        }
        return true;
    }


    public ArrayList<SleepDataRecord> findDailySleepDataInDatabase(String date) {

        if (date == null) {
            return null;
        }
        if (!initializeDatabase()) {
            return null;
        }

        ArrayList<SleepDataRecord> sleepDailyData = null;

        String[] projection = {SleepDataEntry.COLUMN_NAME_START_TIME,SleepDataEntry.COLUMN_NAME_END_TIME,SleepDataEntry.COLUMN_NAME_DURATION,SleepDataEntry.COLUMN_NAME_TYPE};
        String selection = SleepDataEntry.COLUMN_NAME_DATE + "=?";
        String[] selectionArgs = {date};

        Cursor c = mDb.query(
                SleepDataEntry.TABLE_NAME,  // The table to query
                projection,                        // The columns to return
                selection,                         // The columns for the WHERE clause
                selectionArgs,                     // The values for the WHERE clause
                null,                              // don't group the rows
                null,                              // don't filter by row groups
                null                               // The sort order
        );

        if (c.getCount() > 0) {
            //c.moveToFirst();
            sleepDailyData = new  ArrayList<SleepDataRecord>();

            while (c.moveToNext()) {
                String start_time = c.getString(c.getColumnIndexOrThrow(SleepDataEntry.COLUMN_NAME_START_TIME));
                String end_time = c.getString(c.getColumnIndexOrThrow(SleepDataEntry.COLUMN_NAME_END_TIME));
                String duration = c.getString(c.getColumnIndexOrThrow(SleepDataEntry.COLUMN_NAME_DURATION));
                String type = c.getString(c.getColumnIndexOrThrow(SleepDataEntry.COLUMN_NAME_TYPE));

                sleepDailyData.add(new SleepDataRecord(date,start_time,end_time,duration,type));

            }

            return sleepDailyData;
        }
        else {
            return null;
        }
    }


}
