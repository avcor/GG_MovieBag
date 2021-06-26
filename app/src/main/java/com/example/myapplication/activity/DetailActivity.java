package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MovieResponse.MovieDetailResponse;
import com.example.myapplication.MovieResponse.MovieTrendingResponse;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ProductionHousesAdapter;
import com.example.myapplication.adapters.SimilarMovieAdapter;
import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.ProductionCompanyModel;
import com.example.myapplication.request.Servicey;
import com.example.myapplication.utils.Credentials;
import com.example.myapplication.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView movieImageView;
    TextView movieNameTV, movieLanguageTV, movieReleaseDateTV, movieSynopsisTV, popularityTV, release_status;
    RatingBar movieRatingBar;
    Button castButton, reviewButton;
    MovieModel movieModel;

    RecyclerView similarRecyclerView;
    SimilarMovieAdapter similarMovieAdapter;

    int movieID;

    ProductionHousesAdapter productionHousesAdapter;
    RecyclerView productionHousesRecyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // all ids can be found here
        findViewID();

        //setting up everything details of the movie and similar movie
        if(getIntent().getParcelableExtra("movieModel") != null){
            movieModel = getIntent().getParcelableExtra("movieModel");
            movieID = movieModel.getId();
            settingUpText();
            settingUpSimilarMovie();
            Log.d("tag", "onCreate: "+ movieID);
        }

        //button goes here
        castButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CastActivity.class);
            intent.putExtra("movieID",movieModel.getId());
            startActivity(intent);
        });
        reviewButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra("movieID", movieModel.getId());
            startActivity(intent);
        });

    }

    public void settingUpSimilarMovie(){
        // trending and similar ; share same model

        similarMovieAdapter = new SimilarMovieAdapter();
        //setting adapter
        similarRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        similarRecyclerView.setAdapter(similarMovieAdapter);

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieTrendingResponse> responseCall = movieApi.similarMovie(
                movieID,
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<MovieTrendingResponse>() {
            @Override
            public void onResponse(Call<MovieTrendingResponse> call, Response<MovieTrendingResponse> response) {
                if(response.code() == 200){
                    List<MovieModel> similarMovieList = new ArrayList<>(response.body().getMovies());
                    similarMovieAdapter.setmMovieModel(similarMovieList);
                    if (similarMovieList.size() <1){
                        TextView tv = findViewById(R.id.similarMovieTV);
                        tv.append(" N/A");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieTrendingResponse> call, Throwable t) {

            }
        });

        similarMovieAdapter.setOnItemClickListener((movieModel, position) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("movieModel" , movieModel);
            startActivity(intent);
            finish();
        });
    }

    public void settingUpText(){
//        some of the general data can be found in parecable and remaining will be found in detail model
//        there is no model for movie detail, info can be get through reponse. And for production houses there is model
        productionHousesAdapter = new ProductionHousesAdapter();
        productionHousesRecyler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        productionHousesRecyler.setAdapter(productionHousesAdapter);

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieDetailResponse> responseCall = movieApi.detailMovie(
                movieID,
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                Log.d("tag", "onResponse: "+ response);
                if(response.code() == 200){
                    popularityTV.setText("Popularity : " + response.body().getPopularity());
                    release_status.setText("Release Status : " +response.body().getStatus());
                    int productionSize = response.body().getProductionCompanyModel().size();
                    if(productionSize<1){   //0
                        TextView tv = findViewById(R.id.productionTV);
                        tv.append(" N/A");
                    }
                    productionHousesAdapter.setProductionCompanyModels(response.body().getProductionCompanyModel());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {

            }
        });

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + movieModel.getBackdrop_path())
                .into(movieImageView);

        if(movieModel.getOriginal_title() != null)
            movieNameTV.setText(movieModel.getOriginal_title());
        else
            movieNameTV.setText(movieModel.getOriginal_name());

        if(movieModel.getRelease_date() != null)
             movieReleaseDateTV.setText("Release date : " + movieModel.getRelease_date());
        else
            movieReleaseDateTV.setText("Release date : -");

        movieLanguageTV.setText("Language : " + movieModel.getOriginal_language());
        movieSynopsisTV.setText(movieModel.getOverview());
        movieRatingBar.setRating(movieModel.getVote_average()/2);
    }

    public void findViewID(){
        movieImageView = findViewById(R.id.detailMovieIV);
        movieNameTV= findViewById(R.id.detailNameTV);
        movieLanguageTV = findViewById(R.id.detailLanguageTV);
        movieReleaseDateTV = findViewById(R.id.detailReleaseTV);
        movieSynopsisTV = findViewById(R.id.detailSynopsisTV);
        castButton = findViewById(R.id.detailCastButton);
        reviewButton = findViewById(R.id.detailReviewButton);
        movieRatingBar = findViewById(R.id.detailRating);
        similarRecyclerView = findViewById(R.id.similarRecyclerView);
        productionHousesRecyler = findViewById(R.id.production_houses);
        popularityTV = findViewById(R.id.popularity);
        release_status = findViewById(R.id.releaseStatus);
    }
}