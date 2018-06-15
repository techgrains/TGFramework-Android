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
 * TGStringRequest has been created by extending TGRequest which internally uses Volley framework.
 *
 * @param <T> TGResponse
 */
public class TGStringRequest<T extends TGResponse> extends TGRequest<T> {

    /**
     * Initialize TGStringRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     */
    public TGStringRequest(int method, String url, TGIResponseListener listener, TGParams params) {
        super(method, url, listener, params);
    }

    /**
     * Initialize TGStringRequest
     *
     * @param method i.e., TGRequest.Method.POST
     * @param url String
     * @param listener TGIResponseListener
     * @param params TGParams
     * @param type Type (Reflection Type) Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     */
    public TGStringRequest(int method, String url, TGIResponseListener listener, TGParams params, Type type) {
        super(method, url, listener, params, type);
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
            T t = (T) TGUtil.fromJson("{}", getType());
            populateTGResponseCoreInfo(response, t);

            if(listener!=null)
                listener.onSuccessBackgroundThread(t);
            return Response.success(t, HttpHeaderParser.parseCacheHeaders(networkResponse));

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
