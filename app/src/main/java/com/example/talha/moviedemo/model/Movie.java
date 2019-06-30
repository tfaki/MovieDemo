package com.example.talha.moviedemo.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    @SerializedName("results")
    private ArrayList<MovieResult> movieResults;

    public Movie(ArrayList<MovieResult> movieResult) {
        this.movieResults = movieResult;
    }
    public ArrayList<MovieResult> getMovieResult() {
        return movieResults;
    }

    public void setMovieResult(ArrayList<MovieResult> movieResult) {
        this.movieResults = movieResult;
    }

}
