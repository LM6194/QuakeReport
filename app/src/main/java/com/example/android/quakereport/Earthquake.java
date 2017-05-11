package com.example.android.quakereport;



/**
 * Created by Luis on 11/17/2016.
 */

public class Earthquake  {
    // magnitude of the earthquake
    private double mMagnitude;
    // location of the earthquake
    private String mLocation;
    //time of the earthquake
    private long mTimeInMilliseconds;
    // url of the earthquake
    private String mUrl;

    /**
     * Create a new object
     * @param magnitude is the magnitude of the earthquake
     * @param location is where the earthquake  happened
     * @param timeInMilliseconds is time in milliseconds ds (from the Epoch)when the
     *             earthquake happened
     * @param url is the url specific for the earthquake
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;

    }

    /**
     * @return the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * @return the location of the earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * @return the date of the earthquake
     */
    public long getTimeInMilliseconds(){ return mTimeInMilliseconds;}

    /**
     * @return the url the earthquake in question
     */
    public String getUrl(){ return mUrl;}
}
