package com.techgrains.example.model;

import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGJsonRequest;
import com.techgrains.service.TGParams;

public class UserDataRequest extends TGJsonRequest<ApiResponse<UserData>> {
    public UserDataRequest(int method, String url, TGIResponseListener<ApiResponse<UserData>> listener, TGParams params) {
        super(method, url, listener, params);
        setShouldCache(false);
    }
}
