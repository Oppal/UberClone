package com.cuantium.uberclone;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nerdless on 18/03/2015.
 */
public class DirectionsContract {
    public static final String BASE_URL = "http://maps.googleapis.com/maps/api/directions";
    public static final String PATH_JSON = "json";

    public static final String PARAM_ORIGIN = "origin";
    public static final String PARAM_DEST = "destination";
    public static final String PARAM_MODE = "mode";
    public static final String PARAM_SENSOR = "sensor";
    public static final String PARAM_REGION = "region";
    public static final String PARAM_UNITS = "units";

    public static String buildDirectionsUrl (LatLng origin, LatLng destination)
    {
        Uri directUrl = Uri.parse(DirectionsContract.BASE_URL).buildUpon()
                .appendPath(DirectionsContract.PATH_JSON)
                .appendQueryParameter(DirectionsContract.PARAM_MODE, "driving")
                .appendQueryParameter(DirectionsContract.PARAM_REGION, "mx")
                .appendQueryParameter(DirectionsContract.PARAM_UNITS, "metric")
                .appendQueryParameter(DirectionsContract.PARAM_SENSOR, "true")
                .appendQueryParameter(DirectionsContract.PARAM_ORIGIN, String.valueOf(origin.latitude)+","+String.valueOf(origin.longitude))
                .appendQueryParameter(DirectionsContract.PARAM_DEST, String.valueOf(destination.latitude)+","+String.valueOf(destination.longitude))
                .build();


        //Log.i("myLog1", "url: " + directUrl.toString());
        return directUrl.toString();
    }

    public static Direction parseEventsFromString (String response) throws JSONException {
        JSONObject route = new JSONObject(response)
        .getJSONArray("routes")
        .getJSONObject(0)
        .getJSONArray("legs")
        .getJSONObject(0);
        //.getJSONObject("distance");

        //JSONObject duration = new JSONObject(response)
          //      .getJSONArray("routes")
       /*         .getJSONObject(0)
                .getJSONArray("legs")
                .getJSONObject(0)
                .getJSONObject("duration");*/

        int space = route.getJSONObject("distance").getInt("value");
        int time = route.getJSONObject("duration").getInt("value");

        Log.i("myLog1", "space: "+space);
        Log.i("myLog1", "time: "+time);

        ArrayList<Driver> listDrivers = new ArrayList<>();

        Direction direction = new Direction(space, time);

        return direction;
    }






}
