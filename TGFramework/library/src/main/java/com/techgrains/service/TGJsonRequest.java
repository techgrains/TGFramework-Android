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

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.techgrains.error.TGException;
import com.techgrains.util.TGUtil;

import java.lang.reflect.Type;

/**
 * TGJsonRequest has been created by extending TGRequest which internally uses Volley framework.
 * TGJsonRequest encapsulates network calls and conversion of json response to custom T object.
 * It internally uses GSon library for converting string into Java objects. It supports SerializedName expression to map Json element name to java object's attribute name.
 *
 * @param <T> TGResponse
 */
public abstract class TGJsonRequest<T extends TGResponse> extends TGRequest<T> {

    /**
     * Initialize TGJsonRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGJsonRequest(int method, String url, TGIResponseListener<T> listener, TGParams params) {
        super(method, url, listener, params);
    }

    /**
     * Initialize TGJsonRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     * @param type Type (Reflection Type) Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     */
    public TGJsonRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, Type type) {
        super(method, url, listener, params, type);
    }

    /**
     * Initialize TGJsonRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     * @param type Type (Reflection Type) Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     * @param timeout timeout of the request
     * @param maxRetries max retries of the request
     */
    public TGJsonRequest(int method, String url, TGIResponseListener<T> listener, TGParams params, Type type, int timeout, int maxRetries) {
        super(method, url, listener, params, type, timeout, maxRetries);
    }

    /**
     * Parsing the network response into standard TGResponse
     *
     * @param networkResponse NetworkResponse
     * @return {@code Response<T>} where T is instance of TGResponse
     */
    @Override
    final protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        TGResponse response = createTGResponse(networkResponse);
        try {
            T jsonObject = (T)TGUtil.fromJson(response.getNetworkResponse(), getType());
            populateTGResponseCoreInfo(response, jsonObject);

            if(listener!=null && jsonObject!=null)
                listener.onSuccessBackgroundThread(jsonObject);
            return Response.success((T)jsonObject, HttpHeaderParser.parseCacheHeaders(networkResponse));

        } catch (JsonSyntaxException jse) {
            response.setError(new TGException(jse).getError());
            response.getError().setMessage("Unable to convert json response to object. Please match JSon syntax with expected response object." + jse.getMessage());
            response.getError().setDetailMessage(jse.getMessage());
        } catch (ClassCastException cce) {
            cce.printStackTrace();
            response.setError(new TGException(cce).getError());
            response.getError().setMessage("Unable to convert json response to object. " + cce.getMessage());
            response.getError().setDetailMessage(cce.getMessage());
        } catch (Exception e) {
            response.setError(new TGException(e).getError());
            response.getError().setDetailMessage(e!=null ? e.getMessage() : "");
        }
        if(listener!=null)
            listener.onError(response);
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    /**
     * Delivers the response on the main thread on TGIResponseListener
     *
     * @param response TGResponse
     */
    @Override
    final protected void deliverResponse(T response) {
        super.deliverResponse(response);
    }

    /**
     * Delivers the error on TGIResponseListener
     *
     * @param error VolleyError
     */
    @Override
    final public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    /**
     * Creation of TGResponse object based on the NetwworkResponse
     *
     * @param networkResponse NetworkResponse
     * @return TGResponse
     */
    final TGResponse createTGResponse(NetworkResponse networkResponse) {
        return super.createTGResponse(networkResponse);
    }
}
