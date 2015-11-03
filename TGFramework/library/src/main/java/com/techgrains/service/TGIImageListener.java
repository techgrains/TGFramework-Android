package com.techgrains.service;

import android.graphics.Bitmap;

public interface TGIImageListener {
    /**
     * Gives bitmap of the image along with URL.
     * @param bitmap Bitmap
     * @param url String
     */
    public void onLoad(Bitmap bitmap, String url);

    /**
     * Gives detail of error in standard TGResponse object.
     * @param response TGResponse
     */
    public void onError(TGResponse response);
}
