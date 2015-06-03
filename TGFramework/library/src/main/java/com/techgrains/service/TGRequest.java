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
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.techgrains.application.TGApplication;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TGRequest has been created on top of strong Volley framework. TGRequest encapsulates all the hurdle of network calls and enables clean object orientated approach to the service caller.
 *
 * @param <T> where T is instance of TGResponse
 */
public abstract class TGRequest<T extends TGResponse> extends Request<T>{
    final static String LOG_TAG = "TG_LOG";

    private static final String API_HEADER_USERAGENT = "User-Agent";
    private static final String API_HEADER_API = "API";
    private static final String API_HEADER_VERSION = "Ver";

    private static String userAgent = null;

    private TGParams params;
    TGIResponseListener<T> listener;

    private final Type type;
    public Type getType() {
        return this.type;
    }

    /**
     * Intialize TGRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params) {
        super(method, url, null);
        this.listener = listener;
        this.params = params;
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Intialize TGRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     * @param type Type (Reflection Type) Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     */
    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, Type type) {
        super(method, url, null);
        this.listener = listener;
        this.params = params;
        this.type = type;
    }
        /**
         * Params of the TGRequest
         *
         * @return Map
         * @throws AuthFailureError Auth Fails
         */
    final protected Map<String, String> getParams() throws AuthFailureError {
        return params.getParams();
    }

    /**
     * Get Headers. Extend this method to override default behavior of header generation.
     *
     * @return Map
     * @throws AuthFailureError Auth Fails
     */
    @Override
    final public Map<String, String> getHeaders() throws AuthFailureError {
        return getDefaultHeaders(null);
    }

    /**
     * Delivers the response on the main thread on TGIResponseListener
     *
     * @param response TGResponse
     */
    @Override
    protected void deliverResponse(T response) {
        if(response!=null)
            listener.onSuccessMainThread(response);
    }

    /**
     * Delivers the error on TGIResponseListener
     *
     * @param error VolleyError
     */
    @Override
    public void deliverError(VolleyError error) {
        TGResponse response = createTGResponse(error.networkResponse);
        TGError tgError = new TGException(error).getError();
        response.setError(tgError);
        if(response!=null)
            listener.onError(response);
    }

    TGResponse createTGResponse(NetworkResponse networkResponse) {
        TGResponse response = new TGResponse();
        if(networkResponse!=null) {
            response.setStatusCode(networkResponse.statusCode);
            response.setResponse(new String(networkResponse.data));
            response.setHeaders(networkResponse.headers);
            response.setNetworkTimeInMillis(networkResponse.networkTimeMs);
            response.setModified(!networkResponse.notModified);
        }
        return response;
    }

    /**
     * Copy core TGResponse info from source to destination.
     *
     * @param source TGResponse
     * @param jsonObject TGResponse
     */
    void populateTGResponseCoreInfo(TGResponse source, TGResponse jsonObject) {
        if(source!=null && jsonObject!=null) {
            jsonObject.setStatusCode(source.getStatusCode());
            jsonObject.setResponse(new String(source.getResponse()));
            jsonObject.setHeaders(source.getHeaders());
            jsonObject.setNetworkTimeInMillis(source.getNetworkTimeInMillis());
            jsonObject.setModified(source.isModified());
        }
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

}
