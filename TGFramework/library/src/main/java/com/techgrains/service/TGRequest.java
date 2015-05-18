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
package com.techgrains.service;

import android.content.Context;
import android.content.pm.PackageManager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.techgrains.application.TGApplication;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;

import java.util.HashMap;
import java.util.Map;

/**
 * TGRequest has been created on top of strong Volley framework. TGRequest encapsulates all the hurdle of network calls and enables clean object orientated approach to the service caller.
 *
 * @param <T> where T is instance of TGResponse
 */
public class TGRequest <T extends TGResponse> extends Request<T>{
    private static final String API_HEADER_USERAGENT = "User-Agent";
    private static final String API_HEADER_API = "API";
    private static final String API_HEADER_VERSION = "Ver";

    private static String userAgent = null;

    private String serverUrl;
    private String servicePath;
    private TGParams params;
    private Map<String, String> header;
    private TGIResponseListener listener;

    /**
     * Intialize TGRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGRequest(int method, String url, TGIResponseListener listener, TGParams params) {
        super(method, url, null);
        this.listener = listener;
        this.params = params;
    }

    /**
     * Params of the TGRequest
     *
     * @return Map<String, String>
     * @throws AuthFailureError
     */
    final protected Map<String, String> getParams() throws AuthFailureError {
        return params.getParams();
    }

    private static String getUserAgent() {
        if (userAgent == null) {
            final Context appContext = TGApplication.getContext();
            try {
                String packageName = appContext.getPackageName();
                final String version = appContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                final int code = appContext.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                userAgent = packageName + " (Android/" + version + "/" + code + "/" + android.os.Build.MANUFACTURER + "/" + android.os.Build.MODEL
                        + "/" + android.os.Build.VERSION.RELEASE + ")";
            } catch (PackageManager.NameNotFoundException e) {
                // Ignore creation of user agent.
            }
        }
        return userAgent;
    }

    private static Map<String, String> getDefaultHeaders(Map<String, String> header) {
        final Context context = TGApplication.getContext();
        if (header == null)
            header = new HashMap<String, String>();

        header.put(API_HEADER_USERAGENT, getUserAgent());
        header.put(API_HEADER_API, "Android");
        try {
            header.put(API_HEADER_VERSION, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return header;
    }

    /**
     * Get Headers. Extend this method to override default behavior of header generation.
     *
     * @return Map<String, String>
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return getDefaultHeaders(null);
    }

    /**
     * Parsing the network response into standard TGResponse
     *
     * @param networkResponse NetworkResponse
     * @return Response<T> where T is instance of TGResponse
     */
    @Override
    final protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        TGResponse response = createTGResponse(networkResponse);

        listener.onSuccessBackgroundThread(response);

        return (Response<T>) Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    /**
     * Delivers the response on the main thread on TGIResponseListener
     *
     * @param response TGResponse
     */
    @Override
    final protected void deliverResponse(T response) {
        listener.onSuccessMainThread(response);
    }

    /**
     * Delivers the error on TGIResponseListener
     *
     * @param error
     */
    @Override
    final public void deliverError(VolleyError error) {
        TGResponse response = createTGResponse(error.networkResponse);
        response.setNetworkTimeInMillis(error.getNetworkTimeMs());
        TGError tgError = new TGException(error).getError();
        response.setError(tgError);
        listener.onError(response);
    }

    private TGResponse createTGResponse(NetworkResponse networkResponse) {
        TGResponse response = new TGResponse();
        response.setHttpStatusCode(networkResponse.statusCode);
        response.setResponse(new String(networkResponse.data));
        response.setHeaders(networkResponse.headers);
        response.setNetworkTimeInMillis(networkResponse.networkTimeMs);
        response.setModified(!networkResponse.notModified);
        return response;
    }

}
