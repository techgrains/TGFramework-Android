package com.techgrains.example.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
