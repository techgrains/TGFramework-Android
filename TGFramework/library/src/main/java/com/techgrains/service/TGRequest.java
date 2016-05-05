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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.techgrains.application.TGApplication;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;

import com.techgrains.util.TGUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TGRequest has been created on top of strong Volley framework. TGRequest encapsulates all the hurdle of network calls and enables clean object orientated approach to the service caller.
 *
 * @param <T> where T is instance of TGResponse
 */
public abstract class TGRequest<T extends TGResponse> extends Request<T>{
    final static String LOG_TAG = "TG_LOG";

    public static final int DEFAULT_TIMEOUT = (int)TimeUnit.SECONDS.toMillis(30);
    public static final int DEFAULT_MAX_RETRIES = 5;

    private static final String API_HEADER_USERAGENT = "User-Agent";
    private static final String API_HEADER_API = "API";
    private static final String API_HEADER_VERSION = "Ver";

    private static String userAgent = null;

    private TGRequestPriority priority;

    private TGParams params;
    TGIResponseListener<T> listener;

    private final Type type;
    public Type getType() {
        return this.type;
    }

    /**
     * Initialize TGRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params) {
        super(method, Method.GET == method ? TGUtil.appendParamsToUrl(url, params) : url, null);
        setRetryPolicy();
        this.listener = listener;
        this.params = params;
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, int timeout, int maxRetries) {
        super(method, Method.GET == method ? TGUtil.appendParamsToUrl(url, params) : url, null);
        setRetryPolicy(timeout, maxRetries);
        this.listener = listener;
        this.params = params;
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Initialize TGRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     * @param type Type (Reflection Type) Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     */
    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, Type type) {
        super(method, Method.GET == method ? TGUtil.appendParamsToUrl(url, params) : url, null);
        setRetryPolicy();
        this.listener = listener;
        this.params = params;
        this.type = type;
    }

    public TGRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, Type type, int timeout, int maxRetries) {
        super(method, Method.GET == method ? TGUtil.appendParamsToUrl(url, params) : url, null);
        setRetryPolicy(timeout, maxRetries);
        this.listener = listener;
        this.params = params;
        this.type = type;
    }

    private void setRetryPolicy() {
        setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT, DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void setRetryPolicy(int timeout, int maxRetries) {
        setRetryPolicy(new DefaultRetryPolicy(
                timeout, maxRetries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * Params of the TGRequest
     *
     * @return Map
     * @throws AuthFailureError Auth Fails
     */
    final protected Map<String, String> getParams() throws AuthFailureError {
        if(params!=null)
            return params.getParams();
        return null;
    }

    /**
     * Get Headers. Extend this method to override default behavior of header generation.
     *
     * @return Map
     * @throws AuthFailureError Auth Fails
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
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
        if(response!=null) {
            if(error.getClass().equals(TimeoutError.class))
                response.setTimeout(true);
            listener.onError(response);
        }
    }

    TGResponse createTGResponse(NetworkResponse networkResponse) {
        TGResponse response = new TGResponse();
        if(networkResponse!=null) {
            response.setStatusCode(networkResponse.statusCode);
            response.setNetworkResponse(new String(networkResponse.data));
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
            jsonObject.setNetworkResponse(new String(source.getNetworkResponse()));
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

    @Override
    /**
     * Get the current priority of the request. (Low, Normal, High or Immediate)
     */
    final public Priority getPriority() {
        if (priority==null)
            return Priority.NORMAL;

        switch(priority) {
            case LOW:
                return Priority.LOW;
            case NORMAL:
                return Priority.NORMAL;
            case HIGH:
                return Priority.HIGH;
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                return Priority.NORMAL;
        }
    }

    /**
     * Set Request priority via TGRequestPriority
     * @param priority TGRequestPriority
     */
    public void setPriority(TGRequestPriority priority) {
        this.priority = priority;
    }

}
