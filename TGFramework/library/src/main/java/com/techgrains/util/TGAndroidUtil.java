package com.techgrains.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.techgrains.application.TGApplication;

/**
 * Created by CNS on 28/04/15.
 */
public class TGAndroidUtil {

    /**
     * Checks Internet connectivity, returns true is network is available else false
     *
     * @return boolean
     */
    public static boolean isNetworkAvailable() {
        final Context appContext = TGApplication.getContext();
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
