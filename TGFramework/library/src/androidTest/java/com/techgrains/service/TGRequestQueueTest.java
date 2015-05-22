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

import android.content.res.AssetManager;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.techgrains.application.TGApplication;
import com.techgrains.util.TGAndroidUtil;
import com.techgrains.util.TGUtil;

import junit.framework.TestCase;
import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class TGRequestQueueTest  extends TestCase {

    final static String LOG_TAG = "TG_LOG";
    final static String MOCK_DIR = "mock";
    public static TGResponse tgResponse;
    TGRequestQueue requestQueue;

    public void setUp() {
        requestQueue = TGRequestQueue.getInstance();
    }

    public void testReadMockFileAndConvertToJson() throws IOException {
        String filename = MOCK_DIR + "/UserLoginResponse.json";
        String content = TGAndroidUtil.readFileFromAssets(filename);
        // Confirms content having value. Means, read file correctly.
        assertTrue(TGUtil.hasValue(content));

        Log.d(LOG_TAG, content);

        Type type = new TypeToken<UserLoginResponse>(){}.getType();
        UserLoginResponse response = (UserLoginResponse) TGUtil.fromJson(content, type);
        assertNotNull(response);
        assertEquals(15, response.getData().getUser().getId());
        assertEquals("vishal", response.getData().getUser().getName());
        assertEquals(35, response.getData().getUser().getAge());
        assertNotNull(response.getData().getDepartments());
        assertEquals(2, response.getData().getDepartments().size());
        assertEquals("it", response.getData().getDepartments().get(0));
        assertEquals("hr", response.getData().getDepartments().get(1));
    }

    public void testMockFileRequest() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/UserLoginResponse.json";
            TGParams params = new TGParams();
            params.putParam("username", "vishal");
            params.putParam("password", "patel");

            TGIResponseListener listener = new TGIResponseListener() {
                @Override
                public void onSuccessMainThread(TGResponse response) {
                    Log.d(LOG_TAG,"onSuccessMainThread");
                    tgResponse = response;
                }

                @Override
                public void onSuccessBackgroundThread(TGResponse response) {
                    Log.d(LOG_TAG, "onSuccessBackgroundThread");
                    tgResponse = response;
                }

                @Override
                public void onError(TGResponse response) {
                    Log.d(LOG_TAG, "onError");
                    tgResponse = response;
                }
            };

            UserLoginRequest userLoginRequest = new UserLoginRequest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequest);
            Thread.sleep(3000);
            assertEquals("{ERROR:FILE_DOES_NOT_EXIST}", tgResponse.getResponse());

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void tearDown() {
        requestQueue = null;
    }
}

class UserLoginRequest extends TGJsonRequest<UserLoginResponse> {
    public UserLoginRequest(int method, String url, TGIResponseListener listener, TGParams params) {
        super(method, url, listener, params);
        setShouldCache(false);
    }
}

class UserLoginResponse extends TGResponse {
    @SerializedName("data")
    private Data data;
    public Data getData() { return data; }
}

class Data {
    @SerializedName("user")
    User user;
    @SerializedName("departments")
    private List<String> departments;
    public User getUser() { return user; }
    public List<String> getDepartments() { return departments; }
}

class User {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    public long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}
