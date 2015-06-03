package com.techgrains.example.request;

import com.techgrains.example.model.ApiResponse;
import com.techgrains.example.model.CityList;
import com.techgrains.service.TGIResponseListener;
import com.techgrains.service.TGJsonRequest;
import com.techgrains.service.TGParams;

public class CityListRequest extends TGJsonRequest<ApiResponse<CityList>> {
    public CityListRequest(int method, String url, TGIResponseListener<ApiResponse<CityList>> listener, TGParams params) {
        super(method, url, listener, params);
        setShouldCache(false);
    }
}
