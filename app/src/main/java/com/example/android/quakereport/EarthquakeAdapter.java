package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Roiprez on 03/09/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public static final String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context,0, earthquakes);
    }

    public View getView(int position, View convertView, ViewGroup parent){
// Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item, parent, false);
        }



        // Get the {@link AndroidFlavor} object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);

        DecimalFormat mag = new DecimalFormat("0.0");

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitudeTextView.setText(mag.format(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        // The second attribute of the function is a private function to obtain the color according to the magnitude of the earthquake
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        Log.e("ColorCircle", String.valueOf(magnitudeColor));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquake.getLocation();
        String locationOffset, primaryLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        locationTextView.setText(primaryLocation);

        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        distanceTextView.setText(locationOffset);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        dateTextView.setText(currentEarthquake.getDate());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        timeTextView.setText(currentEarthquake.getTime());



        return listItemView;
    }

    private int getMagnitudeColor(Double d) {
        // The floor method finds the closest integer to the magnitude
        int mag = (int) Math.floor(d);
        int toret;
        switch(mag){
            case 0:
            case 1: toret = R.color.magnitude1; break;
            case 2: toret = R.color.magnitude2; break;
            case 3: toret = R.color.magnitude3; break;
            case 4: toret = R.color.magnitude4; break;
            case 5: toret = R.color.magnitude5; break;
            case 6: toret = R.color.magnitude6; break;
            case 7: toret = R.color.magnitude7; break;
            case 8: toret = R.color.magnitude8; break;
            case 9: toret = R.color.magnitude9; break;
            default: toret = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(), toret);
    }


}
