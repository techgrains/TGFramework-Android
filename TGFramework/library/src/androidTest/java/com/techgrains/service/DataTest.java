package com.techgrains.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataTest {
    @SerializedName("user")
    UserTest user;
    @SerializedName("departments")
    private List<String> departments;
    public UserTest getUser() { return user; }
    public List<String> getDepartments() { return departments; }
}
