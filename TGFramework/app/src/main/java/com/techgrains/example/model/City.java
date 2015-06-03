package com.techgrains.example.model;

import com.google.gson.annotations.SerializedName;

public class City {

        @SerializedName("Id")
        private int id;

        @SerializedName("Name")
        private String name;

        @SerializedName("Restaurant")
        private boolean restaurant;

        @SerializedName("Medical")
        private boolean medical;

        @SerializedName("Banquet")
        private boolean banquet;

        @SerializedName("Auto")
        private boolean autobool;

        @SerializedName("RealEstate")
        private boolean realEstate;

        @SerializedName("Latitude")
        private long latitude;

        @SerializedName("Longitude")
        private long longitude;

        @SerializedName("Location")
        private String[] location;


        public String[] getLocation() {
            return location;
        }

        public void setLocation(String[] location) {
            this.location = location;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isRestaurant() {
            return restaurant;
        }

        public void setRestaurant(boolean restaurant) {
            this.restaurant = restaurant;
        }

        public boolean isMedical() {
            return medical;
        }

        public void setMedical(boolean medical) {
            this.medical = medical;
        }

        public boolean isBanquet() {
            return banquet;
        }

        public void setBanquet(boolean banquet) {
            this.banquet = banquet;
        }

        public boolean isAutobool() {
            return autobool;
        }

        public void setAutobool(boolean autobool) {
            this.autobool = autobool;
        }

        public boolean isRealEstate() {
            return realEstate;
        }

        public void setRealEstate(boolean realEstate) {
            this.realEstate = realEstate;
        }

        public long getLatitude() {
            return latitude;
        }

        public void setLatitude(long latitude) {
            this.latitude = latitude;
        }

        public long getLongitude() {
            return longitude;
        }

        public void setLongitude(long longitude) {
            this.longitude = longitude;
        }

}
