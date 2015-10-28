package com.paper.andreas.smarthome;


public class SmartPlug {

    private String mId;
    private String mApplianceConnected;
    private String mLocation;
    private boolean mConnection;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmApplianceConnected() {
        return mApplianceConnected;
    }

    public void setmApplianceConnected(String mApplianceConnected) {
        this.mApplianceConnected = mApplianceConnected;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public boolean ismConnection() {
        return mConnection;
    }

    public void setmConnection(boolean mConnection) {
        this.mConnection = mConnection;
    }

    public SmartPlug(String id, String appliance, String location, boolean connection){
        mId = id;
        mApplianceConnected = appliance;
        mLocation = location;
        mConnection = connection;

    }
}
