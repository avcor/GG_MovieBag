package com.example.myapplication.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.AppExecutor;
import com.example.myapplication.MovieResponse.MovieTrendingResponse;
import com.example.myapplication.MovieResponse.NowMovieResponse;
import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.NowMovieModel;
import com.example.myapplication.utils.Credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> mMovies;
    private MutableLiveData<List<NowMovieModel>> mNowMovies;

    private static MovieApiClient instance;
    private RetrieveMoviewRunnable retrieveMoviewRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null){
            instance = new MovieApiClient();
        }return  instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
        mNowMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }
    public LiveData<List<NowMovieModel>> getNowMovies(){
        return mNowMovies;
    }

    public void trendingMovieApi(int pageNumber){
        if(retrieveMoviewRunnable != null){
            retrieveMoviewRunnable = null;
        }
        retrieveMoviewRunnable = new RetrieveMoviewRunnable(pageNumber, true);

        final Future myHandler = AppExecutor.getInstance().getmNetworkIO().submit(retrieveMoviewRunnable);

        AppExecutor.getInstance().getmNetworkIO().schedule((Runnable) () -> {
            myHandler.cancel(true);
        },4000, TimeUnit.MILLISECONDS);
    }
    public void nowMovieApi(){
        if(retrieveMoviewRunnable != null){
            retrieveMoviewRunnable = null;
        }
        retrieveMoviewRunnable = new RetrieveMoviewRunnable(true);

        final Future myHandler = AppExecutor.getInstance().getmNetworkIO().submit(retrieveMoviewRunnable);

        AppExecutor.getInstance().getmNetworkIO().schedule((Runnable) () -> {
            myHandler.cancel(true);
        },4000, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMoviewRunnable implements Runnable{

        private int pageNumber, movieID;
        boolean cancelRequest=false, trending=false, nowMovie=false;

        public RetrieveMoviewRunnable(int pageNumber, boolean trending){
            this.pageNumber = pageNumber;
            this.trending = trending;
            this.cancelRequest =false;
        }
        public RetrieveMoviewRunnable(boolean nowMovie){
            this.trending = false;
            this.cancelRequest =false;
            this.nowMovie = nowMovie;
        }


        @Override
        public void run() {
            try{
                Response response;
                // trending trending movies
                if (trending)    {
                    response = getTrending(pageNumber).execute();
                    if(response.code() == 200){
                        List<MovieModel>list = new ArrayList<>(((MovieTrendingResponse)response.body()).getMovies());
                        if(pageNumber == 1){
                            // sending data to live data
                            mMovies.postValue(list);
                        }else{
                            List<MovieModel> currentMovies = mMovies.getValue();
                            currentMovies.addAll(list);
                            mMovies.postValue(currentMovies);
                        }
                    }else{
                        Log.d("tag", "run: " + response.errorBody().string());
                        mMovies.postValue(null);
                    }
                }
                // getting now watching Movies
                else if(nowMovie){
                    response = getNowMovies().execute();

                    if(response.code() == 200){
                        Log.d("tag", "run: " + "sdfaaaaaaaaaaa");
                        List<NowMovieModel>list = new ArrayList<>(((NowMovieResponse)response.body()).getNowMovies());
                            mNowMovies.postValue(list);
                    }else{
                        Log.d("tag", "run: " + response.errorBody().string());
                        mMovies.postValue(null);
                    }
                }

                if(cancelRequest){ return; }
            }
            catch (Exception e){}

        }

        // methods
        private Call<MovieTrendingResponse> getTrending (int pageNumber){
            return Servicey.getMovieApi().trending(
                    Credentials.API_KEY,
                    "en-US",
                    pageNumber
            );
        }

        private Call<NowMovieResponse> getNowMovies(){
            return Servicey.getMovieApi().nowMovie(
                    Credentials.API_KEY
            );
        }
        private void cancelRequest(){
            Log.d("tag", "cancelRequest: ");
            cancelRequest = true;
        }

    }

}
