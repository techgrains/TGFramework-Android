package com.techgrains.example.service;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.techgrains.example.model.ApiResponse;
import com.techgrains.example.model.CityList;
import com.techgrains.example.model.UserData;
import com.techgrains.example.request.CityListRequest;
import com.techgrains.example.request.GenericRequest;
import com.techgrains.example.request.UserDataRequest;
import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGParams;
import com.techgrains.service.TGRequest;
import com.techgrains.service.TGRequestQueue;
import com.techgrains.service.TGResponse;

import java.lang.reflect.Type;

public class GenericServiceTest extends BaseInstrumentationTestCase {

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

    public void testMockFileGenericRequest_Success() throws InterruptedException {
        try {
            int method = TGRequest.Method.POST;
            String url = "mock/GenericResponse.json";

            TGParams params = new TGParams();

            TGIResponseListener<ApiResponse<UserData>> listener = new TGIResponseListener<ApiResponse<UserData>>() {
                @Override
                public void onSuccessMainThread(ApiResponse<UserData> response) {
                    Log.d(LOG, "onSuccessMainThread");
                    Log.d(LOG, "Name = " + response.getRes().getData().getUser().getName());
                }

                @Override
                public void onSuccessBackgroundThread(ApiResponse<UserData> response) {
                    Log.d(LOG, "onSuccessBackgroundThread");
                    Log.d(LOG, "Email = " + response.getRes().getData().getUser().getName());
                }

                @Override
                public void onError(TGResponse response) {
                    Log.d(LOG, "onError");
                    Log.d(LOG, "Message = " + response.getError().getMessage());
                }
            };

            GenericRequest genericRequest = createGenericRequest(url, listener, params);
            TGRequestQueue.getInstance().addRequest(genericRequest);
            Thread.sleep(3000);

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    private static GenericRequest createGenericRequest(String url, TGIResponseListener<?> listener, TGParams params) {
        Type type = new TypeToken<ApiResponse<UserData>>(){}.getType();
        return new GenericRequest(TGRequest.Method.POST, url, listener, params,type);
    }

}

