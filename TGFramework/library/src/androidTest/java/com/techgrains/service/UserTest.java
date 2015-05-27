package com.techgrains.service;

import com.google.gson.annotations.SerializedName;

public class UserTest {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    public long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}
