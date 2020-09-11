package com.techiv.leaderboard.dto;

import android.graphics.Bitmap;

public class StudentIQ {
    private String name;
    private String country;
    private Bitmap badge;
    private String score;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
