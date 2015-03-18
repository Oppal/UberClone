package com.cuantium.uberclone;

/**
 * Created by nerdless on 05/03/2015.
 */
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventfulContract {
    public static final String BASE_URL = "http://52.10.104.56/";
    public static final String PATH_JSON = "json";
    public static final String PATH_EVENTS = "events";
    public static final String PATH_SEARCH = "search";
    public static final String PATH_DRIVER = "driver";
    public static final String PATH_FIND_DRIVER = "findClosestDrivers";

    public static final String PARAM_LATITUDE = "latitude";
    public static final String PARAM_LONGITUDE = "longitude";

    public static final String URL_EVENTS_SEARCH = "/" + PATH_JSON + "/" + PATH_EVENTS + "/" + PATH_SEARCH;

    public static final String PARAM_LOCATION = "location";
    public static final String PARAM_APP_KEY = "app_key";
    public static final String PARAM_DATE = "date";

    public static final String APP_KEY = "4Q7hC5bJXwHMZ99t";
    public static final String VALUE_THIS_WEEK = "This Week";

    public static final String JSON_TAG_NAME = "name";
    public static final String JSON_TAG_EMAIL = "email";
    public static final String JSON_TAG_LATITUDE = "latitude";
    public static final String JSON_TAG_LONGITUDE = "longitude";
    public static final String JSON_KEY_TITLE_EVENT = "title";

    public static String getSearchEventsUrl (String location){
        Uri eventUrl = Uri.parse(EventfulContract.BASE_URL).buildUpon()
                .appendPath(EventfulContract.PATH_JSON)
                .appendPath(EventfulContract.PATH_EVENTS)
                .appendPath(EventfulContract.PATH_SEARCH)
                .appendQueryParameter(EventfulContract.PARAM_APP_KEY, EventfulContract.APP_KEY)
                .appendQueryParameter(EventfulContract.PARAM_LOCATION, location)
                .appendQueryParameter(EventfulContract.PARAM_DATE, VALUE_THIS_WEEK)
                .build();



        return eventUrl.toString();
    }

    public static String buildSearchDriversUrl (double latitude, double longitude){
        Uri eventUrl = Uri.parse(EventfulContract.BASE_URL).buildUpon()
                .appendPath(EventfulContract.PATH_DRIVER)
                .appendPath(EventfulContract.PATH_FIND_DRIVER)
                .appendQueryParameter(EventfulContract.PARAM_LATITUDE, String.valueOf(latitude))
                .appendQueryParameter(EventfulContract.PARAM_LONGITUDE, String.valueOf(longitude))
                .build();

        Log.i("myLog", "url "+ eventUrl.toString());

        return eventUrl.toString();
    }

    public static ArrayList<Driver> parseEventsFromString (String response) throws JSONException {
        JSONArray drivers = new JSONArray(response);
                //.getJSONObject(JSON_KEY_EVENTS)
                //.getJSONObject(JSON_KEY_PACKAGE_EVENTS)
                //.getJSONArray(JSON_KEY_EVENTS);

        ArrayList<Driver> listDrivers = new ArrayList<>();

        for (int i = 0; i < drivers.length(); i++) {
            JSONObject currentDriver = drivers.getJSONObject(i);

            String name =  currentDriver.getString(JSON_TAG_NAME);
            String email = currentDriver.getString(JSON_TAG_EMAIL);
            Double latitude =  currentDriver.getDouble(JSON_TAG_LATITUDE);
            Double longitude =  currentDriver.getDouble(JSON_TAG_LONGITUDE);

            Driver driver = new Driver(name, email, latitude, longitude);
            listDrivers.add(driver);
        }

        return listDrivers;
    }
}
