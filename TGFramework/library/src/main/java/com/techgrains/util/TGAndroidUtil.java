/*
 * Copyright 2015 Techgrains Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techgrains.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.techgrains.application.TGApplication;

import java.io.IOException;

/**
 * TGAndroidUtil is differnet than TGUtil in terms of Android classes depedencies.
 * (i.e., Network Availability relies on Android's Application Context via ConnectivityManager.
 */
public class TGAndroidUtil {

    /**
     * Checks Internet connectivity, returns true is network is available else false
     * @return boolean
     */
    public static boolean isNetworkAvailable() {
        final Context appContext = TGApplication.getContext();
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Reads file from asset.
     * @param file String - Relative path of the file in asset
     * @return String - Content of the file
     * @throws IOException if unable to read input stream
     */
    public static String readFileFromAssets(String file) throws IOException {
        return TGUtil.readInputStream(TGApplication.getContext().getResources().getAssets().open(file));
    }

}
