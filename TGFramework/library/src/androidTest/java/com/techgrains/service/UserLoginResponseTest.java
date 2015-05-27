package com.techgrains.service;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponseTest extends TGResponse {
    @SerializedName("data")
    private DataTest data;
    public DataTest getDataTest() { return data; }
}
