package com.example.myapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.Holder> {

    List<MovieModel> movieModels;
    private OnItemClickListener listener;

    public void setmMovieModel(List<MovieModel> mMovieModel){
        this.movieModels = mMovieModel;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movie_layout,parent,false);
        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        Glide.with(holder.Iv.getContext())
                .load("https://image.tmdb.org/t/p/w500"+ movieModels.get(position).getPoster_path())
                .into(holder.Iv);
        holder.movieName.setText(movieModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(movieModels != null){
            return movieModels.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView movieName;
        ImageView Iv;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.similarMovieName);
            Iv = itemView.findViewById(R.id.similarImageMovie);

            itemView.setOnClickListener(view -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(movieModels.get(position), position);
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
