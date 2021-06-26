package com.example.myapplication.MovieResponse;

import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCastResponse {
    @SerializedName("cast")
    @Expose()
    private List<CastModel> cast;

    public List<CastModel> getCast(){
        return cast;
    }

    @Override
    public String toString() {
        return "MovieCastResponse{" +
                "cast=" + cast +
                '}';
    }
}
