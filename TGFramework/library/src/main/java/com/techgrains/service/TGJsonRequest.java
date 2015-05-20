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

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.techgrains.error.TGException;
import com.techgrains.util.TGUtil;

import java.lang.reflect.Type;

/**
 * TGJsonRequest has been created by extending TGRequest which internally uses Volley framework.
 * TGJsonRequest encapsulates network calls and conversion of json response to custom T object.
 * It internally uses GSon library for converting string into Java objects. It supports SerializedName expression to map Json element name to java object's attribute name.
 *
 * @param <T>
 */
public class TGJsonRequest<T extends TGResponse> extends TGRequest<T> {

    /**
     * Initialize TGJsonRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGJsonRequest(int method, String url, TGIResponseListener listener, TGParams params) {
        super(method, url, listener, params);
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
            Type type = new TypeToken<T>(){}.getType();
            T jsonObject = (T) TGUtil.fromJson(response.getTgResponseString(), type);

            // Successful Json conversion to T object
            listener.onSuccessBackgroundThread(jsonObject);
            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(networkResponse));

        } catch (JsonSyntaxException jse) {
            response.setTgError(new TGException(jse).getError());
        }

        // Unsuccessful Json conversion return core TGResponse having response string
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
