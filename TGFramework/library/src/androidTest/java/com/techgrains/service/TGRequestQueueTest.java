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

import com.google.gson.reflect.TypeToken;
import com.techgrains.util.TGAndroidUtil;
import com.techgrains.util.TGUtil;

import junit.framework.TestCase;

import java.io.IOException;
import java.lang.reflect.Type;

public class TGRequestQueueTest  extends TestCase {

    final static String LOG_TAG = "TG_LOG";
    final static String MOCK_DIR = "mock";
    private static TGResponse tgResponse;
    private static UserLoginResponseTest userLoginResponseTest;
    private TGRequestQueue requestQueue;

    public void setUp() {
        requestQueue = TGRequestQueue.getInstance();
    }

    public void testClassTypeCreationUsingTypeToken() {
        Type type = new TypeToken<TGResponse>(){}.getType();
        Object jsonObject = TGUtil.fromJson("{}", type);
        assertNotNull(jsonObject);
        assertTrue(jsonObject instanceof TGResponse);
    }

    public void testReadMockFileAndConvertToJson() throws IOException {
        String filename = MOCK_DIR + "/UserLoginResponse.json";
        String content = TGAndroidUtil.readFileFromAssets(filename);

        // Confirms content having value. Means, read file correctly.
        assertTrue(TGUtil.hasValue(content));

//        Log.d(LOG_TAG, "File Content:" + content);

        Type type = new TypeToken<UserLoginResponseTest>(){}.getType();
        UserLoginResponseTest response = (UserLoginResponseTest) TGUtil.fromJson(content, type);
        assertUserLoginResponse(response);
    }

    public void testMockFileRequestSuccess() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/UserLoginResponse.json";
            TGParams params = new TGParams();
            params.putParam("username", "vishal");
            params.putParam("password", "patel");

            TGIResponseListener<UserLoginResponseTest> listener = getTgiResponseListenerImpl();

            UserLoginRequestTest userLoginRequestTest = new UserLoginRequestTest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequestTest);
            Thread.sleep(3000);
            assertUserLoginResponse(userLoginResponseTest);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void testMockFileRequestFailure() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/FileNotExists.json";
            TGParams params = new TGParams();

            TGIResponseListener<UserLoginResponseTest> listener = getTgiResponseListenerImpl();

            UserLoginRequestTest userLoginRequestTest = new UserLoginRequestTest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequestTest);
            Thread.sleep(3000);
            assertEquals(200, userLoginResponseTest.getStatusCode());
            assertEquals("{ERROR:FILE_DOES_NOT_EXIST}", userLoginResponseTest.getResponse());

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    private TGIResponseListener<UserLoginResponseTest> getTgiResponseListenerImpl() {
        return new TGIResponseListener<UserLoginResponseTest>() {
            @Override
            public void onSuccessMainThread(UserLoginResponseTest response) {
                Log.d(LOG_TAG, "onSuccessMainThread");
                userLoginResponseTest = response;
            }

            @Override
            public void onSuccessBackgroundThread(UserLoginResponseTest response) {
                Log.d(LOG_TAG, "onSuccessBackgroundThread");
                userLoginResponseTest = response;
            }

            @Override
            public void onError(TGResponse response) {
                Log.d(LOG_TAG, "Callback: onError");
                Log.d(LOG_TAG, "response="+response.getClass());
                Log.d(LOG_TAG, "code="+response.getError().getCode());
                Log.d(LOG_TAG, "message="+response.getError().getMessage());
                Log.d(LOG_TAG, "response="+response.getResponse());
                tgResponse = response;
            }
        };
    }

    private void assertUserLoginResponse(UserLoginResponseTest userLoginResponseTest) {
        assertNotNull(userLoginResponseTest);
        assertEquals(15, userLoginResponseTest.getDataTest().getUser().getId());
        assertEquals("vishal", userLoginResponseTest.getDataTest().getUser().getName());
        assertEquals(35, userLoginResponseTest.getDataTest().getUser().getAge());
        assertNotNull(userLoginResponseTest.getDataTest().getDepartments());
        assertEquals(2, userLoginResponseTest.getDataTest().getDepartments().size());
        assertEquals("it", userLoginResponseTest.getDataTest().getDepartments().get(0));
        assertEquals("hr", userLoginResponseTest.getDataTest().getDepartments().get(1));
    }

    public void tearDown() {
        requestQueue = null;
        tgResponse = null;
        userLoginResponseTest = null;
    }
}

