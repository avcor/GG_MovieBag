package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TrendingMovieAdapter extends RecyclerView.Adapter<TrendingMovieAdapter.MovieViewHolder> {
    private List<MovieModel> mMovies;
    private OnItemClickListener listener;

    public void setmMovies(List<MovieModel> mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    public TrendingMovieAdapter() {  /*empty constructor*/  }
    public TrendingMovieAdapter(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
    }

    @NonNull
    @NotNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_layout,parent,false);
       return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieViewHolder holder, int position) {
        if(mMovies.get(position).getOriginal_title() != null)
            holder.movieNameTV.setText(mMovies.get(position).getOriginal_title());
        else
            holder.movieNameTV.setText(mMovies.get(position).getOriginal_name());

        if(mMovies.get(position).getRelease_date() != null)
            holder.releasedateTV.setText("Release date : " + mMovies.get(position).getRelease_date());
        else
            holder.releasedateTV.setText("To be released");

        holder.languageTV.setText("Language : " + mMovies.get(position).getOriginal_language());
        holder.ratingBar.setRating(mMovies.get(position).getVote_average()/2);
        Glide.with(holder.movieIV)
                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
                .into(holder.movieIV);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null){
            return mMovies.size();
        }
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder  {
        ImageView  movieIV;
        TextView movieNameTV, languageTV, releasedateTV;
        RatingBar ratingBar;
        public MovieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            releasedateTV = itemView.findViewById(R.id.releaseDate);
            movieNameTV = itemView.findViewById(R.id.movieName);
            languageTV = itemView.findViewById(R.id.language);
            ratingBar = itemView.findViewById(R.id.movieRating);
            movieIV = itemView.findViewById(R.id.movieImage);

            itemView.setOnClickListener(view ->{
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(mMovies.get(position), position);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(MovieModel movieModel, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
