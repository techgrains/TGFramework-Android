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

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;

/**
 * TGImageRequest has been created by extending TGRequest which internally uses Volley framework. It fetches image provide back as Bitmap.
 */
public class TGImageRequest extends ImageRequest {

    private TGResponse response;
    private TGIResponseListener<TGResponse> listener;

    public TGImageRequest(String url, TGIResponseListener<TGResponse> listener) {
        this(url, listener, 0, 0, null, null);
    }

    public TGImageRequest(String url, TGIResponseListener<TGResponse> listener, Bitmap.Config decodeConfig) {
        this(url, listener, 0, 0, null, decodeConfig);
    }

    public TGImageRequest(String url, TGIResponseListener<TGResponse> listener, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, Bitmap.Config decodeConfig) {
        super(url, null, maxWidth, maxHeight, scaleType, decodeConfig, null);
        this.listener = listener;
    }

    @Override
    final protected Response<Bitmap> parseNetworkResponse(NetworkResponse networkResponse) {
        createResponse(networkResponse);
        Response<Bitmap> bitmapResponse = super.parseNetworkResponse(networkResponse);
        if(bitmapResponse!=null && bitmapResponse.isSuccess()) {
            response.setBitmap(bitmapResponse.result);
            if(listener!=null)
                listener.onSuccessBackgroundThread(response);
        }
        return bitmapResponse;
    }

    /**
     * Delivers the response on the main thread on TGIResponseListener
     *
     * @param bitmap Bitmap
     */
    @Override
    protected void deliverResponse(Bitmap bitmap) {
        response.setBitmap(bitmap);
        if(listener!=null)
            listener.onSuccessMainThread(response);
    }

    /**
     * Delivers the error on TGIResponseListener
     *
     * @param error VolleyError
     */
    @Override
    public void deliverError(VolleyError error) {
        createResponse(error.networkResponse);
        TGError tgError = new TGException(error).getError();
        response.setError(tgError);
        if(listener!=null && response!=null)
            listener.onError(response);
    }

    private void createResponse(NetworkResponse networkResponse) {
        response = new TGResponse();
        if(networkResponse!=null) {
            response.setStatusCode(networkResponse.statusCode);
            response.setNetworkResponse(new String(networkResponse.data));
            response.setHeaders(networkResponse.headers);
            response.setNetworkTimeInMillis(networkResponse.networkTimeMs);
            response.setModified(!networkResponse.notModified);
        }
    }

}
