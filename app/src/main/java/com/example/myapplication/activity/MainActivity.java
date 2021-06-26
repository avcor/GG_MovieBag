package com.example.myapplication.activity;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.MovieListViewModel.MovieListViewModel;
import com.example.myapplication.R;
import com.example.myapplication.adapters.NowWatchingAdapter;
import com.example.myapplication.adapters.TrendingMovieAdapter;
import com.example.myapplication.models.NowMovieModel;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private MovieListViewModel trendingMovieListViewModel, nowMovieListViewModel ;
    private RecyclerView recyclerView;
    private TrendingMovieAdapter trendingMovieAdapter;
    private NowWatchingAdapter nowWatchingAdapter;
    private ViewPager2 horizontalViewPager;
    int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        horizontalViewPager = findViewById(R.id.nowWatchingRecyclerView);

        //setups trending movies
        setupTrendingRecyclerView();

        //setup now playing movies in theater
        setupNowMovie();
    }

    public void setupNowMovie(){
        nowMovieListViewModel =  new ViewModelProvider(this).get(MovieListViewModel.class);
        nowWatchingAdapter = new NowWatchingAdapter();

        horizontalViewPager.setClipToPadding(false);
        horizontalViewPager.setClipChildren(false);
        horizontalViewPager.setOffscreenPageLimit(3);
        horizontalViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        horizontalViewPager.setAdapter(nowWatchingAdapter);

        nowMovieListViewModel.nowMovieApi();
        nowMovieListViewModel.getNowMovies().observe(this, nowMovieModels -> {
            nowWatchingAdapter.setmNowMovie(nowMovieModels);
//            nowWatchingAdapter.setOnItemClickListener((nowMovieModel, position) -> {
//                Intent intent = new Intent(this, DetailActivity.class);
//                intent.putExtra("movieID" , nowMovieModel.getId());
//                startActivity(intent);
//            });
        });

    }

    public void setupTrendingRecyclerView(){

        trendingMovieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        trendingMovieAdapter = new TrendingMovieAdapter();
        recyclerView.setAdapter(trendingMovieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trendingMovieListViewModel.trendingMovieApi(pageNumber);

        trendingMovieListViewModel.getMovies().observe(this, movieModels -> {
            if(movieModels != null){
                trendingMovieAdapter.setmMovies((movieModels));
            }
        });

        // this is for pagination
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    // next page to be called at the end of the recycler View
                    trendingMovieListViewModel.trendingMovieApi(++pageNumber);
                }
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        trendingMovieAdapter.setOnItemClickListener((movieModel, position) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("movieModel" , movieModel);
            startActivity(intent);
        });
    }

}