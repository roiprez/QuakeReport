package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Roiprez on 03/09/2016.
 */
public class Earthquake {
    private double magnitude;
    private String location;
    private long time;
    private String url;


    public Earthquake(double magnitude, long time, String location, String url){
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
        this.url = url;

    }

    public String getUrl(){
        return url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        Date dateObject = new Date(time);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }
    public String getTime(){
        Date dateObject = new Date(time);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObject);
    }


    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude=" + getMagnitude() +
                ", location='" + getLocation() + '\'' +
                ", date='" + getDate() + '\'' +
                '}';
    }
}
