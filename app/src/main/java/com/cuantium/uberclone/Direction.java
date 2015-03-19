package com.cuantium.uberclone;

/**
 * Created by nerdless on 18/03/2015.
 */
public class Direction {
    int distance;
    int time;

        public Direction (int distance, int time)
    {
        this.distance = distance;
        this.time = time;
    }

    public Integer getDistance() { return distance; }

    public Integer getTime() { return time; }
}
