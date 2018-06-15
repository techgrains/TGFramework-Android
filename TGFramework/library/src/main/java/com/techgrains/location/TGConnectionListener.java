package com.techgrains.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;

/**
 * Created by admin on 27/02/18.
 */

public interface TGConnectionListener extends LocationListener {
    void onConnectionFailed(@NonNull ConnectionResult connectionResult);

    void onConnectionSuspended(int i);

    void onConnected(Bundle bundle, Location location);

}
