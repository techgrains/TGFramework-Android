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

import com.techgrains.common.TGObject;

/**
 * TGService is the TGFramework's service layer wrapper class.
 */
public class TGService extends TGObject {

    /**
     * Performs TGJsonRequest which gives TGResponse based on the JSon output.
     *
     * @param request TGJsonRequest
     */
    public static void performJsonRequest(TGJsonRequest<?> request) {
        TGRequestQueue.getInstance().addRequest(request);
    }

    /**
     * Performs TGStringRequest which gives TGResponse based on the output.
     *
     * @param request TGStringRequest
     */
    public static void performStringRequest(TGStringRequest<?> request) {
        TGRequestQueue.getInstance().addRequest(request);
    }

    /**
     * Performs TGImageRequest which gives TGResponse based on the output.
     *
     * @param request TGImageRequest
     */
    public static void performImageRequest(TGImageRequest request) {
        TGRequestQueue.getInstance().addImageRequest(request);
    }

    /**
     * Performs TGJsonBodyRequest which gives TGResponse based on the output.
     *
     * @param request TGJsonBodyRequest
     */
    public static void performJsonBodyRequest(TGJsonBodyRequest request) {
        TGRequestQueue.getInstance().addRequest(request);
    }

    /**
     * Cancels the request to be performed. If request has been initiated on the network already, it will drop the response and ignores to update listeners.
     *
     * @param request TGRequest
     */
    public static void cancelRequest(TGRequest request) {
        request.cancel();
    }

    /**
     * Cancel requests by tag.
     *
     * @param tag Object
     */
    public static void cancelRequestByTag(Object tag) {
        TGRequestQueue.getInstance().cancelRequestByTag(tag);
    }

    /**
     * Start requests to be performed from the dispatcher
     */
    public static void startRequests() {
        TGRequestQueue.getInstance().startRequests();
    }

    /**
     * Stop request to be dispatched further
     */
    public static void stopRequests() {
        TGRequestQueue.getInstance().stopRequests();
    }

    /**
     * Load Image with in memory LRU cache.
     *
     * @param imageUrl String
     * @param imageView ImageView
     */
    public static void loadImage(String imageUrl, ImageView imageView) {
        TGRequestQueue.getInstance().loadImage(imageUrl, imageView, 0, 0);
    }

    /**
     * Load Image with in memory LRU cache.
     *
     * @param imageUrl String
     * @param imageView ImageView
     * @param defaultImageId Resource Id for default image
     * @param errorImageId Resource Id for error image
     */
    public static void loadImage(String imageUrl, ImageView imageView, int defaultImageId, int errorImageId) {
        TGRequestQueue.getInstance().loadImage(imageUrl, imageView, defaultImageId, errorImageId);
    }

    /**
     * Load Image with in memory LRU cache but callback goes back to the listener.
     *
     * @param imageUrl String
     * @param imageListener TGIImageLIstener
     */
    public static void loadImage(String imageUrl, TGIImageListener imageListener) {
        TGRequestQueue.getInstance().loadImage(imageUrl, imageListener);
    }

}