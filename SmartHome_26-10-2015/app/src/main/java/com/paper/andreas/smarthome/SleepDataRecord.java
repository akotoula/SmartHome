package com.paper.andreas.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adreas on 17/10/2015.
 */
public class SleepDataRecord  {

    private final String mDate;
    private final String mStartTime;
    private final String mEndTime;
    private final String mDuration;
    private final String mType0;

    public SleepDataRecord(String mDate, String mStartTime, String mEndTime, String mDuration, String mType0) {
        this.mDate = mDate;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mDuration = mDuration;
        this.mType0 = mType0;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public String getmDuration() {
        return mDuration;
    }

    public String getmType0() {
        return mType0;
    }


}
