package com.techgrains.example.request;

import com.techgrains.example.model.ApiResponse;
import com.techgrains.example.model.UserData;
import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGJsonRequest;
import com.techgrains.service.TGParams;

public class UserDataRequest extends TGJsonRequest<ApiResponse<UserData>> {
    public UserDataRequest(int method, String url, TGIResponseListener<ApiResponse<UserData>> listener, TGParams params) {
        super(method, url, listener, params);
        setShouldCache(false);
    }
}
