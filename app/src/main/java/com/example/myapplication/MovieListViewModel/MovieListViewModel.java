package com.example.myapplication.MovieListViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.NowMovieModel;
import com.example.myapplication.repositry.Movierepositry;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private Movierepositry movierepositry;

    public MovieListViewModel(){
        movierepositry = Movierepositry.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movierepositry.getMovies();
    }
    public LiveData<List<NowMovieModel>> getNowMovies() {return movierepositry.getNowMovies(); }

    public void trendingMovieApi(int pageNumber){
        movierepositry.trendingMovieApi(pageNumber);
    }
    public void nowMovieApi() { movierepositry.nowMovieApi();}
}
