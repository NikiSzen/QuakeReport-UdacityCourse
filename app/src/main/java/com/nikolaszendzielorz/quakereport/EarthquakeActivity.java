package com.nikolaszendzielorz.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        double magn = 4.3;
        String myData = "Feb 2, 2016";

        // App should:
        // 1. FetchEarthquakeDataTask on an given url.
        new FetchEarthquakeDataTask().execute(USGS_REQUEST_URL);
        //      it will update the UI in the postExecute method

        /*
        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link QuakeArrayAdapter} of earthquakes
        final QuakeArrayAdapter adapter = new QuakeArrayAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = adapter.getItem(position);
                String url = currentEarthquake.getURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });*/
    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(ArrayList<Earthquake> earthquakes) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link QuakeArrayAdapter} of earthquakes
        final QuakeArrayAdapter adapter = new QuakeArrayAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = adapter.getItem(position);
                // if(currentEarthquake != null)
                String url = currentEarthquake.getURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

    private class FetchEarthquakeDataTask extends AsyncTask<String,Void,ArrayList<Earthquake>>{
        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if(urls.length <1 || urls[0] == null){
                return null;
            }

            // Perform the HTTP request for earthquake data and process the response.
            ArrayList<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            updateUi(earthquakes);
        }
    }
}
