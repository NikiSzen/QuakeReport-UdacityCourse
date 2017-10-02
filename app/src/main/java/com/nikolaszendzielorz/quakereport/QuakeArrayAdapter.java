package com.nikolaszendzielorz.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Nicole on 24.04.2017.
 */

public class QuakeArrayAdapter extends ArrayAdapter<Earthquake> {

    public QuakeArrayAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view RECYCLING :)
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude_textView);
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        magTextView.setText(output);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude ad set it.
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        TextView placeDetailsTextView = (TextView) listItemView.findViewById(R.id.location_details_textView);
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.location_textView);
        //check if getPlace() contains "of"
        String tempPlace = currentEarthquake.getPlace();
        String placeDetails ="Near the ";
        String place = tempPlace;
        if(tempPlace.contains("of")){
            int tempIndex = tempPlace.indexOf("of ");
            tempIndex +=3;
            // true >  [0 to "of"] + [rest of it]
            placeDetails = tempPlace.substring(0,tempIndex);
            place = tempPlace.substring(tempIndex); //,tempPlace.length()
        }

        placeDetailsTextView.setText(placeDetails);
        placeTextView.setText(place);

        //placeTextView.setText(currentEarthquake.getPlace());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date_textView);
        dateView.setText(currentEarthquake.getDate());
        TextView timeView = (TextView) listItemView.findViewById(R.id.time_textView);
        timeView.setText(currentEarthquake.getTime());
/*
        listItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = currentEarthquake.getURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);
            }
        });*/

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude){
        int circleColorId;
        int mag = (int) Math.floor(magnitude);

        switch (mag){
            case 0:
            case 1:
                circleColorId = R.color.magnitude1;
                break;
            case 2:
                circleColorId = R.color.magnitude2;
                break;
            case 3:
                circleColorId = R.color.magnitude3;
                break;
            case 4:
                circleColorId = R.color.magnitude4;
                break;
            case 5:
                circleColorId = R.color.magnitude5;
                break;
            case 6:
                circleColorId = R.color.magnitude6;
                break;
            case 7:
                circleColorId = R.color.magnitude7;
                break;
            case 8:
                circleColorId = R.color.magnitude8;
                break;
            case 9:
                circleColorId = R.color.magnitude9;
                break;
            default:
                circleColorId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), circleColorId);
    }
}
