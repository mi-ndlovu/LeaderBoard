package com.techiv.leaderboard.dto;

import android.graphics.Bitmap;

public class Student {
    private String name;
    private String country;
    private Bitmap badge;
    private String hours;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Bitmap getBadge() {
        return badge;
    }

    public void setBadge(Bitmap badge) {
        this.badge = badge;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
