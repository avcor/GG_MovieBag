package com.example.myapplication.MovieResponse;

import com.example.myapplication.models.NowMovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;

public class NowMovieResponse {
    @SerializedName("results")
    @Expose()
    private List<NowMovieModel> nowMovies;

    public List<NowMovieModel> getNowMovies(){
        return nowMovies;
    }

    @Override
    public String toString() {
        return "NowMovieResponse{" +
                "nowMovies=" + nowMovies +
                '}';
    }
}
