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
import com.techgrains.service.model.ApiResponse;
import com.techgrains.service.model.CityList;
import com.techgrains.service.model.CityListRequest;
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
    private static ApiResponse<CityList> apiResponseCityList;

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

    public void testMockFileRequest_Success() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/UserLoginResponse.json";
            TGParams params = new TGParams();
            params.putParam("username", "vishal");
            params.putParam("password", "patel");

            TGIResponseListener<UserLoginResponseTest> listener = getTgiResponseListenerImpl();

            UserLoginRequestTest userLoginRequestTest = new UserLoginRequestTest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequestTest);
            Thread.sleep(2000);
            assertUserLoginResponse(userLoginResponseTest);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void testMockFileRequestCityList_Success() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/CityListResponse.json";
            TGParams params = new TGParams();

            TGIResponseListener<ApiResponse<CityList>> listener = new TGIResponseListener<ApiResponse<CityList>>() {
                @Override
                public void onSuccessMainThread(ApiResponse<CityList> response) {
                    Log.d(LOG_TAG, "onSuccessMainThread");
                    apiResponseCityList = response;
                }

                @Override
                public void onSuccessBackgroundThread(ApiResponse<CityList> response) {
                    Log.d(LOG_TAG, "onSuccessBackgroundThread");
                    apiResponseCityList = response;
                }

                @Override
                public void onError(TGResponse response) {
                    Log.d(LOG_TAG, "onError");
                    tgResponse = response;
                }
            };

            CityListRequest cityListRequest = new CityListRequest(method, url, listener, params);
            requestQueue.addRequest(cityListRequest);
            Thread.sleep(2000);
            assertCityListResponse(apiResponseCityList);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void testMockFileRequest_FileNotExists() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/FileNotExists.json";
            TGParams params = new TGParams();

            TGIResponseListener<UserLoginResponseTest> listener = getTgiResponseListenerImpl();

            UserLoginRequestTest userLoginRequestTest = new UserLoginRequestTest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequestTest);
            Thread.sleep(2000);
            assertEquals("{ERROR:FILE_DOES_NOT_EXIST}", userLoginResponseTest.getResponse());

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void testMockFileRequest_InvalidFile() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/UserLoginResponseInvalid.json";
            TGParams params = new TGParams();

            TGIResponseListener<UserLoginResponseTest> listener = getTgiResponseListenerImpl();

            UserLoginRequestTest userLoginRequestTest = new UserLoginRequestTest(method, url, listener, params);
            requestQueue.addRequest(userLoginRequestTest);
            Thread.sleep(2000);
            assertNull(userLoginResponseTest);
            assertEquals(904, tgResponse.getError().getCode());
            assertTrue(tgResponse.getError().getMessage().startsWith("Unable to convert json response to object."));

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

    private void assertCityListResponse(ApiResponse<CityList> apiResponseCityList) {
        assertNotNull(apiResponseCityList);
        assertEquals(true, apiResponseCityList.getRes().isSuccess());
        assertEquals(3, apiResponseCityList.getRes().getData().getCity().size());
        assertEquals("Vadodara", apiResponseCityList.getRes().getData().getCity().get(0).getName());
    }

    public void tearDown() {
        requestQueue = null;
        tgResponse = null;
        userLoginResponseTest = null;
    }
}

