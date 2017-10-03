package com.nikolaszendzielorz.quakereport;

//import android.content.AsyncTaskLoader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Nicole on 03.10.2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        //Log.i("Earthquake Loader", "onStartLoading method");
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        //Log.i("Earthquake Loader", "loadInBackground method");
        if(mUrl == null){
            return null;
        }

        // Perform the HTTP request for earthquake data and process the response.
        ArrayList<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakeList;
    }


}
