package com.techgrains.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityList {

    @SerializedName("City")
    public List<City> city;

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }
}
