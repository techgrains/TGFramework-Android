package com.techgrains.example.model;

import com.google.gson.annotations.SerializedName;

public class Response<T>{

    @SerializedName("Success")
    private boolean success;

    @SerializedName("Data")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
