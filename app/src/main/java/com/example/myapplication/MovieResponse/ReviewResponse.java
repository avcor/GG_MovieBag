package com.example.myapplication.MovieResponse;

import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.ReviewModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("results")
    @Expose()
    private List<ReviewModel> reviewList;

    @SerializedName("total_results")
    @Expose()
    private int totalResults;

    public List<ReviewModel> getReviews(){
        return reviewList;
    }
    public int getTotalResults(){
        return totalResults;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewList=" + reviewList +
                ", totalResults=" + totalResults +
                '}';
    }
}
