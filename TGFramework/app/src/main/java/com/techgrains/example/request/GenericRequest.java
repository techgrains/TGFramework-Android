package com.techgrains.example.request;

import com.techgrains.example.model.ApiResponse;
import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGJsonRequest;
import com.techgrains.service.TGParams;

import java.lang.reflect.Type;

public class GenericRequest<T> extends TGJsonRequest<ApiResponse<T>> {
    public GenericRequest(int method, String url, TGIResponseListener<ApiResponse<T>> listener, TGParams params, Type type) {
        super(method, url, listener, params, type);
    }
}

