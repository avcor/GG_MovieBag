package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.MovieListViewModel.MovieListViewModel;
import com.example.myapplication.MovieResponse.ReviewResponse;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CastRecyclerAdapter;
import com.example.myapplication.adapters.ReviewRecyclerAdapter;
import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.ReviewModel;
import com.example.myapplication.request.Servicey;
import com.example.myapplication.utils.Credentials;
import com.example.myapplication.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    int movieId;
    RecyclerView reviewRecyclerView;
    ReviewRecyclerAdapter reviewRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        movieId = getIntent().getIntExtra("movieID",337404);

        setupReviewRecycler();
    }

    public void setupReviewRecycler(){
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        reviewRecyclerAdapter = new ReviewRecyclerAdapter();
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieApi movieApi = Servicey.getMovieApi();
        Call<ReviewResponse> responseCall = movieApi.review(
                movieId,
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if(response.code() == 200){
                    List<ReviewModel> review = new ArrayList<>(response.body().getReviews());
                    reviewRecyclerAdapter.setmReview(review);
                    reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
                    if (review.size()<1){
                        TextView tv = findViewById(R.id.reviewTV);
                        tv.append(" N/A");
                    }
                }
            }
            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
            }
        });
    }
}