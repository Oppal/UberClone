package com.cuantium.uberclone;

/**
 * Created by nerdless on 12/03/2015.
 */
public class Driver {
    String name;
    String email;
    Double latitude;
    Double longitude;

    public Driver(String name, String email, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
