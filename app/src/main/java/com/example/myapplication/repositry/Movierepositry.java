package com.example.myapplication.repositry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.NowMovieModel;
import com.example.myapplication.request.MovieApiClient;

import java.util.List;

public class Movierepositry {

    private static  Movierepositry instance;
    private MovieApiClient movieApiClient;
    private String mQuery;
    private int mPageNumber;

    // singelton pattern
    public static  Movierepositry getInstance(){
        if(instance == null){
            instance = new Movierepositry();
        }
        return instance;
    }
    private Movierepositry(){
        movieApiClient = MovieApiClient.getInstance();
    }


    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }
    public LiveData<List<NowMovieModel>> getNowMovies(){ return movieApiClient.getNowMovies(); }

    public void trendingMovieApi(int pageNumber){
        movieApiClient.trendingMovieApi(pageNumber);
    }
    public void nowMovieApi(){movieApiClient.nowMovieApi();}
}
