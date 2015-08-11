package com.techgrains.geo;

import android.location.Location;
import android.location.LocationListener;

public interface TGLocationListener extends LocationListener {

    /**
     * Called when accurate location has been captured based on accuracy setting.
     * @param location Location
     */
    void onAccurateLocation(Location location);

    /**
     * Called when LocationManager starts capturing location.
     */
    void onStart();

    /**
     * Called when process timeout based on timeout setting.
     */
    void onTimeOut();
}
