package com.example.talha.moviedemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieResult implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;


    public MovieResult(String title,double popularity, String posterPath) {
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
