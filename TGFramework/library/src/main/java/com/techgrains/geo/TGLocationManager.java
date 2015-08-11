package com.techgrains.geo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.techgrains.application.TGApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Custom location manager class which uses android.location.LocationManager to capture current location with accuracy and timeout settings.
 * @see LocationManager
 */
public class TGLocationManager implements LocationListener {
    // Default variables
    private static final int DEFAULT_TIMEOUT = 30000;
    private static final float DEFAULT_TARGET_ACCURACY = 0.0f;

    // Internal tracking attributes
    private float currentAccuracy = 0.0f;
    private Boolean isFirstTimeGetAccuracy = true;
    private Timer locationUpdateTimer;

    // Android location manager instance
    private LocationManager locationManager;

    // Callback and configurable attributes
    private TGLocationListener listener;
    private float targetAccuracy = 0.0f;
    private int timeout = DEFAULT_TIMEOUT;

    /**
     * Start capturing location with 30 secs of default timeout.
     * @param listener TGLocationListener
     */
    public TGLocationManager(TGLocationListener listener) {
        this(listener, DEFAULT_TARGET_ACCURACY, DEFAULT_TIMEOUT);
    }

    /**
     * Start capturing location with target accuracy of the location with 30 secs of default timeout.
     * @param listener TGLocationListener
     * @param targetAccuracy float
     */
    public TGLocationManager(TGLocationListener listener, float targetAccuracy) {
        this(listener, targetAccuracy, DEFAULT_TIMEOUT);
    }

    /**
     * Start capturing location with target accuracy of the location and provided timeout.
     * @param listener TGLocationListener
     * @param targetAccuracy float
     * @param timeout int
     */
    public TGLocationManager(TGLocationListener listener, float targetAccuracy, int timeout) {
        this.listener = listener;
        this.targetAccuracy = targetAccuracy;
        this.timeout = timeout;
        start();
    }

    /**
     * Restarts capturing location
     */
    public void restart() {
        start();
    }

    private void start() {
        isFirstTimeGetAccuracy = true;
        stop();

        startUpdateTimer();
        locationManager = (LocationManager) TGApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        if(listener!=null)
            listener.onStart();
    }

    private void startUpdateTimer() {
        stopUpdateTimer();
        locationUpdateTimer = new Timer();
        locationUpdateTimer.schedule(new TimerTask() {
            public void run() {
                stop();
                if (listener != null)
                    listener.onTimeOut();
            }
        }, timeout);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(listener!=null)
            listener.onLocationChanged(location);

        if (location.getAccuracy() <= currentAccuracy || isFirstTimeGetAccuracy) {
            isFirstTimeGetAccuracy = false;
            currentAccuracy = location.getAccuracy();
        }

        if (location.getAccuracy() <= targetAccuracy) {
            onAccurateLocation(location);
        }
    }

    private void onAccurateLocation(Location location) {
        stopUpdateTimer();
        removeLocationManager();

        // Callback
        if(listener!=null)
            listener.onAccurateLocation(location);
    }

    /**
     * Stops capturing location
     */
    public void stop() {
        stopUpdateTimer();
        removeLocationManager();
    }

    private void stopUpdateTimer() {
        if (locationUpdateTimer != null) {
            locationUpdateTimer.cancel();
        }
    }

    private void removeLocationManager() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
            locationManager = null;
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if(listener!=null)
            listener.onProviderDisabled(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        if(listener!=null)
            listener.onProviderEnabled(provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if(listener!=null)
            listener.onStatusChanged(provider, status, extras);
    }

}
