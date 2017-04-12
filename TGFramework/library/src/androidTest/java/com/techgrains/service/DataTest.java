package com.techgrains.service;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    @SerializedName("user")
    UserTest user;
    @SerializedName("departments")
    private List<String> departments = new ArrayList<String>();

    @SerializedName("Options")
    private List<String> options = new ArrayList<String>();

    public UserTest getUser() { return user; }
    public List<String> getDepartments() { return departments; }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
