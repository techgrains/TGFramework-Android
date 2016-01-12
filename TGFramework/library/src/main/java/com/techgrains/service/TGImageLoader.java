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

import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;

/**
 * Custom Image Loader class to be used along with Lru Cache
 */
public class TGImageLoader {
    private static TGImageLoader instance;

    private static ImageLoader imageLoader;

    public static TGImageLoader getInstance(RequestQueue queue, ImageLoader.ImageCache imageCache) {
        if(instance==null) {
            instance = new TGImageLoader(queue, imageCache);
        }
        return instance;
    }

    private TGImageLoader(RequestQueue queue, ImageLoader.ImageCache imageCache) {
        imageLoader = new ImageLoader(queue, imageCache);
    }

    public void loadImage(String imageUrl, ImageView imageView, int defaultImageId, int errorImageId) {
        imageLoader.get(imageUrl, ImageLoader.getImageListener(imageView, defaultImageId, errorImageId));
    }

    public void loadImage(String imageUrl, final TGIImageListener tgiImageListener) {
        ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                tgiImageListener.onLoad(response.getBitmap(), response.getRequestUrl());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                TGResponse response = new TGResponse();
                TGError tgError = new TGException(error).getError();
                response.setError(tgError);
                if(response!=null) {
                    if(error.getClass().equals(TimeoutError.class))
                        response.setTimeout(true);
                    tgiImageListener.onError(response);
                }

            }
        };

        imageLoader.get(imageUrl, imageListener);
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }
}