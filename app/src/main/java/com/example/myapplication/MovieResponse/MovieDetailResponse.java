package com.example.myapplication.MovieResponse;

import com.example.myapplication.models.ProductionCompanyModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// response will be directly stored in the model and returned back with no list

public class MovieDetailResponse {
    @SerializedName("popularity")
    @Expose()
    private String popularity;

    @SerializedName("status")
    @Expose()
    private String status;

    @SerializedName("production_companies")
    @Expose()
    List <ProductionCompanyModel> productionCompanyModel;

    public String getPopularity() {
        return popularity;
    }

    public String getStatus() {
        return status;
    }

    public List<ProductionCompanyModel> getProductionCompanyModel() {
        return productionCompanyModel;
    }
}
