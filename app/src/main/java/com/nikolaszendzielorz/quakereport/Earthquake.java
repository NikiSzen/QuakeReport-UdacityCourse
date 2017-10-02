package com.nikolaszendzielorz.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nicole on 24.04.2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private Long mTime;
    private Date mDateObject;
    private String mURL;

    public Earthquake(double magnitude,String place, Long time, String url){
        mMagnitude = magnitude;
        mPlace = place;
        mTime = time;

        mDateObject = new Date(time);
        mURL = url;
    }

    public double getMagnitude(){return mMagnitude;}
    public String getPlace(){return mPlace;}
    public String getDate()
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormatter.format(mDateObject);
    }
    public String getTime()
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("H:mm a");
        return dateFormatter.format(mDateObject);
    }

    public String getURL(){
        return mURL;
    }
}
