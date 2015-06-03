package com.techgrains.example.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("User")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
