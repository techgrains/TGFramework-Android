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

import android.webkit.URLUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.techgrains.application.TGApplication;
import com.techgrains.util.TGAndroidUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class TGRequestQueue {
    private static TGRequestQueue instance = null;

    private static final String DEFAULT_CACHE_DIR = "TGRequestQueue";
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private static final int MAX_DISK_CACHE_BYTES = -1;

    private RequestQueue httpRequestQueue;
    private RequestQueue fileRequestQueue;

    /**
     * Gives singleton instance of TGRequestQueue
     * @return TGRequestQueue
     */
    public static TGRequestQueue getInstance() {
        if(instance == null)
            instance = new TGRequestQueue();

        return instance;
    }

    private TGRequestQueue() {
        fileRequestQueue = createFileRequestQueue();
        httpRequestQueue = createHttpRequestQueue();
    }

    private static RequestQueue createHttpRequestQueue() {
        RequestQueue queue = new RequestQueue(getDiskBasedCache(), new BasicNetwork(new HurlStack()), DEFAULT_NETWORK_THREAD_POOL_SIZE);
        queue.start();
        return queue;
    }

    private static RequestQueue createFileRequestQueue() {
        RequestQueue queue = new RequestQueue(getDiskBasedCache(), new BasicNetwork(new FileStack()), DEFAULT_NETWORK_THREAD_POOL_SIZE);
        queue.start();
        return queue;
    }

    private static DiskBasedCache getDiskBasedCache() {
        File cacheDir = new File(TGApplication.getContext().getCacheDir(), DEFAULT_CACHE_DIR);
        return MAX_DISK_CACHE_BYTES<=-1
                ? new DiskBasedCache(cacheDir)
                : new DiskBasedCache(cacheDir, MAX_DISK_CACHE_BYTES);
    }

    /**
     * Camncel all requests having tag attached with them.
     * @param tag Object
     */
    public void cancelRequestByTag(Object tag) {
        httpRequestQueue.cancelAll(tag);
    }

    /**
     * Start requests to be performed from the dispatcher
     */
    public void startRequests() {
        httpRequestQueue.start();
    }

    /**
     * Stop request to be dispatched further
     */
    public void stopRequests() {
        httpRequestQueue.stop();
    }

    /**
     * Adds provided TGRequest into the appropriate queue.
     * @param request TGRequest
     */
    public void addRequest(TGRequest<?> request) {
        if(URLUtil.isNetworkUrl(request.getUrl()))
            httpRequestQueue.add(request);
        else
            fileRequestQueue.add(request);
    }

}

class FileStack implements HttpStack {

    private static final String LOGGER_TAG = "TG_LOG";
    private static final int SIMULATED_DELAY_MS = 500;

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
            // Ignores simulated delay on interruption.
        }

        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "OK"));
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd mmm yyyy HH:mm:ss zzz");
        response.setHeader(new BasicHeader("Date", dateFormat.format(new Date())));
        response.setLocale(Locale.getDefault());
        response.setEntity(createEntity(request));
        return response;
    }

    private HttpEntity createEntity(Request request) throws UnsupportedEncodingException {
        try {
            String string = TGAndroidUtil.readFileFromAssets(request.getUrl());
            return new StringEntity(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new StringEntity("{ERROR:FILE_DOES_NOT_EXIST}");
        } catch (IOException e) {
            e.printStackTrace();
            return new StringEntity("{ERROR:NOT_ABLE_TO_READ_FILE}");
        }
    }
}