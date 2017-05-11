package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by Luis on 11/17/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARETOR = "of";

    /**
     * Constructs a new {@Link EarthquakeAdapter}
     *
     * @param context     of the app
     * @param earthquakes id the list of earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * return the formatted date string (i.e. "Mar 4, 1984") from a date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * return the formatted date string (i.e. "4:30 Pm")
     */

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    /**
     * returns the correct color value based on the current earthquake's
     * magnitude value
     */
    private  int getMagnitudeColor(double magnitude){
        int magnitudeResourceId;
        int eathquakeMag = (int)Math.floor(magnitude);
        switch (eathquakeMag){
            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeResourceId);

    }


    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list earthquake
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        // find earthquake at a given position in the earthquake list
        Earthquake currentEarthquake = getItem(position);

        // find TextView for id magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        //Display the magnitude of the current earthquake in that TextView
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Store the location for the current Earthquake
        String originalLocation = currentEarthquake.getLocation();
        // string variable that hold the two part of the location
        String locationOffset;
        String primaryLocation;

        // check if the location contain the type (i.e. "49 km NE of")
        if(originalLocation.contains(LOCATION_SEPARETOR)){
            String[] part = originalLocation.split(LOCATION_SEPARETOR);
            locationOffset = part[0]+LOCATION_SEPARETOR;
            primaryLocation = part[1];
        }
        else {
            locationOffset = "Near the";
            primaryLocation = originalLocation;
        }

        // find TextView for id location offset
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationTextView.setText(locationOffset);
        // find TextView for id primary location
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);


        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // find TextView for id  date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the  date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView with view id time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);


        // return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
