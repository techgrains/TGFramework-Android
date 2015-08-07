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
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import android.util.Log;
import android.provider.Settings;

import com.techgrains.application.TGApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * TGAndroidUtil is differnet than TGUtil in terms of Android classes depedencies.
 * (i.e., Network Availability relies on Android's Application Context via ConnectivityManager.
 */
public class TGAndroidUtil {

    private static final String LOG_TAG ="TGF";

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

    /**
     * Gets Display Metric based on the device.
     * @return int Density DPI from DisplayMetric
     */
    public static int getDisplayMetric() {
        return TGApplication.getContext().getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * Logs provided logText with "TGF" tag and android.util.Log.DEBUG log level
     * @param logText String
     */
    public static void log(String logText) {
        log(Log.DEBUG, LOG_TAG, logText);
    }

    /**
     * Logs provided logText in provided tag with android.util.Log.DEBUG log level
     * @param tag String
     * @param logText String
     */
    public static void log(String tag, String logText) {
        log(Log.DEBUG, tag, logText);
    }

    /**
     * Logs provided logText with "TGF" tag with  given logLevel (like, android.util.Log.DEBUG)
     * @param logLevel int VERBOSE, DEBUG, INFO, WARN, ERROR
     * @param logText String
     */
    public static void log(int logLevel, String logText) {
        log(logLevel, LOG_TAG, logText);
    }

    /**
     * Logs provided logText in provided tag at given logLevel (like, android.util.Log.DEBUG)
     * @param logLevel int VERBOSE, DEBUG, INFO, WARN, ERROR
     * @param tag String
     * @param logText String
     */
    public static void log(int logLevel, String tag, String logText) {
        switch(logLevel) {
            case Log.VERBOSE:
                Log.v(tag, logText);
                break;

            case Log.DEBUG:
                Log.d(tag, logText);
                break;

            case Log.INFO:
                Log.i(tag, logText);
                break;

            case Log.WARN:
                Log.w(tag, logText);
                break;

            case Log.ERROR:
                Log.e(tag, logText);
                break;
        }
    }

    /**
     * Converts Bitmap into Byte array using PNG compress format.
     * @param bitmap Bitmap
     * @return byte[]
     */
    public static byte[] convertBitmapToByte(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }
        return null;
    }

    /**
     * Converts Byte array into Bitmap
     * @param bytes byte[]
     * @return Bitmap
     */
    public static Bitmap convertByteArrayToBitmap(byte[] bytes) {
        if (bytes != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        }
        return null;
    }

    /**
     * Get IMEI code
     * @return String IMEI
     */
    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) TGApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        if (TGUtil.hasValue(deviceId))
            return deviceId;
        return Settings.Secure.getString(TGApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Check current device is tablet or not!
     * @return boolean
     */
    public static boolean isTablet() {
        return (TGApplication.getContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Serialize any object into String
     * @param object Object
     * @return String
     * @throws IOException If unable to access Stream
     */
    public static String serialize(Object object) throws java.io.IOException {
        if(object == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new Base64OutputStream(byteArrayOutputStream, 0));
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
        return byteArrayOutputStream.toString();
    }

    /**
     * Deserialize provided string into Object
     * @param serialized String
     * @return Object
     * @throws IOException If unable to access Stream
     * @throws ClassNotFoundException If unable to find class
     */
    public static Object deserialize(String serialized) throws IOException, ClassNotFoundException {
        if(serialized == null)
            return null;
        return new ObjectInputStream(new Base64InputStream(new ByteArrayInputStream(serialized.getBytes()), 0)).readObject();
    }

}
