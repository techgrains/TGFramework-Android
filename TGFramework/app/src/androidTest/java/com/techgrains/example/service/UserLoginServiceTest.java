package com.techgrains.example.service;

import android.util.Log;

import com.techgrains.example.model.ApiResponse;
import com.techgrains.example.model.CityList;
import com.techgrains.example.request.CityListRequest;
import com.techgrains.example.model.UserData;
import com.techgrains.example.request.UserDataRequest;
import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGParams;
import com.techgrains.service.TGRequest;
import com.techgrains.service.TGRequestQueue;
import com.techgrains.service.TGResponse;

public class UserLoginServiceTest extends BaseInstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Log.d(LOG, "setUp()");
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        Log.d(LOG, "tearDown()");
    }

    public void testMockFileRequestCityList_Success() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/CityListResponse.json";
            TGParams params = new TGParams();

            TGIResponseListener<ApiResponse<CityList>> listener = new TGIResponseListener<ApiResponse<CityList>>() {
                @Override
                public void onSuccessMainThread(ApiResponse<CityList> response) {
                    Log.d(LOG, "onSuccessMainThread");
                    Log.d(LOG, response.getResponse());
                    Log.d(LOG, "City name = " + response.getRes().getData().getCity().get(0).getName());
                }

                @Override
                public void onSuccessBackgroundThread(ApiResponse<CityList> response) {
                    Log.d(LOG, "onSuccessBackgroundThread");
                    Log.d(LOG, response.getResponse());
                    Log.d(LOG, "City name = " + response.getRes().getData().getCity().get(0).getName());
                }

                @Override
                public void onError(TGResponse response) {
                    Log.d(LOG, "onError");
                    Log.d(LOG, "Message = " + response.getError().getMessage());
                }
            };

            CityListRequest cityListRequest = new CityListRequest(method, url, listener, params);
            TGRequestQueue.getInstance().addRequest(cityListRequest);
            Thread.sleep(3000);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public void testMockFileRequestUserData_Success() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/UserLogin.json";
            TGParams params = new TGParams();

            TGIResponseListener<ApiResponse<UserData>> listener = new TGIResponseListener<ApiResponse<UserData>>() {
                @Override
                public void onSuccessMainThread(ApiResponse<UserData> response) {
                    Log.d(LOG, "onSuccessMainThread");
                    Log.d(LOG, response.getResponse());
                    Log.d(LOG, "User name = " + response.getRes().getData().getUser().getName());
                }

                @Override
                public void onSuccessBackgroundThread(ApiResponse<UserData> response) {
                    Log.d(LOG, "onSuccessBackgroundThread");
                    Log.d(LOG, response.getResponse());
                    Log.d(LOG, "User name = " + response.getRes().getData().getUser().getName());
                }

                @Override
                public void onError(TGResponse response) {
                    Log.d(LOG, "onError");
                    Log.d(LOG, "Message = " + response.getError().getMessage());
                }
            };

            UserDataRequest userDataRequest = new UserDataRequest(method, url, listener, params);
            TGRequestQueue.getInstance().addRequest(userDataRequest);
            Thread.sleep(3000);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
}
