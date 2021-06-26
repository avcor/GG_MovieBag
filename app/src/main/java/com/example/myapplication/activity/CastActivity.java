package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.MovieResponse.MovieCastResponse;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CastRecyclerAdapter;
import com.example.myapplication.models.CastModel;
import com.example.myapplication.request.Servicey;
import com.example.myapplication.utils.Credentials;
import com.example.myapplication.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastActivity extends AppCompatActivity {

    RecyclerView castRecyclerView;
    CastRecyclerAdapter castRecyclerAdapter;
    int movieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        movieID = getIntent().getIntExtra("movieID", 337404);
        setupCastRecyclerView();
    }

    public void setupCastRecyclerView(){

        castRecyclerView = findViewById(R.id.castRecylerView);
        castRecyclerAdapter = new CastRecyclerAdapter();
        GridLayoutManager lLayout_artist = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false);
        castRecyclerView.setLayoutManager(lLayout_artist);
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieCastResponse> responseCall = movieApi.cast(
                movieID,
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<MovieCastResponse>() {
            @Override
            public void onResponse(Call<MovieCastResponse> call, Response<MovieCastResponse> response) {
                if(response.code() == 200){
                    List<CastModel> castModelList = new ArrayList<>(response.body().getCast());
                    castRecyclerAdapter.setmCast(castModelList);
                    castRecyclerView.setAdapter(castRecyclerAdapter);
                    if (castModelList.size()<1){
                        TextView tv = findViewById(R.id.castTV);
                        tv.append(" N/A");
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieCastResponse> call, Throwable t) {

            }
        });
    }
}