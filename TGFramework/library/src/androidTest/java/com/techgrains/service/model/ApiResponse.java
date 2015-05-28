package com.techgrains.service.model;

import com.google.gson.annotations.SerializedName;
import com.techgrains.service.TGResponse;

public class ApiResponse<T> extends TGResponse {
    @SerializedName("Response")
    private Response<T> res;

    public Response<T> getRes() {
        return res;
    }

    public void setRes(Response<T> res) {
        this.res = res;
    }
}