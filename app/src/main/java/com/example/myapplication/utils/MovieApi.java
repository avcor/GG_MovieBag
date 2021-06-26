package com.example.myapplication.utils;

import com.example.myapplication.MovieResponse.MovieCastResponse;
import com.example.myapplication.MovieResponse.MovieDetailResponse;
import com.example.myapplication.MovieResponse.MovieTrendingResponse;
import com.example.myapplication.MovieResponse.NowMovieResponse;
import com.example.myapplication.MovieResponse.ReviewResponse;
import com.example.myapplication.models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

//    searching movie
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

//    trending movies
//    https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    @GET("/3/trending/all/day")
    Call<MovieTrendingResponse> trending(
        @Query("api_key") String key,
        @Query("language") String language,
        @Query("page") int page
    );

//    credits
//    https://api.themoviedb.org/3/movie/337404/credits?api_key=be987ce88c504c5f76a5a08d19679302&language=en-US
//    https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US
    @GET("/3/movie/{movie_id}/credits")
    Call<MovieCastResponse> cast(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key
    );

//    reviews
//    https://api.themoviedb.org/3/movie/337404/reviews?api_key=be987ce88c504c5f76a5a08d19679302&language=en-US&page=1
    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewResponse> review(
            @Path("movie_id") int movieID,
            @Query("api_key") String key
    );

//    now playing movies in theater
//  https://api.themoviedb.org/3/movie/now_playing?api_key=be987ce88c504c5f76a5a08d19679302&language=en-US&page=1
    @GET("/3/movie/now_playing")
    Call<NowMovieResponse> nowMovie(
            @Query("api_key") String key
    );

//    similar movies to specific movieID
    @GET("/3/movie/{movie_id}/similar")
    Call<MovieTrendingResponse> similarMovie(
            @Path("movie_id") int id,
            @Query("api_key") String key
    );

//    get details of the movie
    @GET("/3/movie/{movie_id}?")
    Call<MovieDetailResponse> detailMovie(
            @Path("movie_id") int id,
            @Query("api_key") String Key
    );
}
