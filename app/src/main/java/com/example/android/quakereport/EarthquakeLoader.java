package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.util.List;

/**
 * Created by Luis on 3/2/2017.
 */

/**
 * Loads a list of earthquakes by using an AsynkTask to perfom the network request
 * to the given URL.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activety
     * @param url    to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading(){
        Log.i(LOG_TAG, "TEST: onStartLoading() called...");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called...");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response,
        //and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
