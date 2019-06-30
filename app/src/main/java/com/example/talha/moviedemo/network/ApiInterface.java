package com.example.talha.moviedemo.network;

import com.example.talha.moviedemo.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<Movie> getUpcomingMovies(@Query("api_key") String apiKey);
}
