package com.example.myapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.MovieModel;
import com.example.myapplication.models.NowMovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NowWatchingAdapter extends RecyclerView.Adapter<NowWatchingAdapter.Holder> {

    List<NowMovieModel> mNowMovie;
    private OnItemClickListener listener;

    public void NowWatchingAdapter(){

    }
    public void setmNowMovie(List<NowMovieModel> mNowMovie) {
        this.mNowMovie = mNowMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_watching_layout,parent,false);
        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.Name.setText(mNowMovie.get(position).getTitle());
        holder.ReleaseDate.setText(mNowMovie.get(position).getRelease_date());

        Glide.with(holder.MovieIV.getContext())
                .load("https://image.tmdb.org/t/p/w500"+ mNowMovie.get(position).getBackdrop_path())
                .into(holder.MovieIV);
    }

    @Override
    public int getItemCount() {
        if(mNowMovie != null){
            return mNowMovie.size();
        }

        else
            return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView MovieIV;
        TextView Name;
        TextView ReleaseDate;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            MovieIV = itemView.findViewById(R.id.nowMovieIV);
            Name = itemView.findViewById(R.id.nowMovieName);
            ReleaseDate = itemView.findViewById(R.id.nowReleaseDate);

            itemView.setOnClickListener(view ->{
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(mNowMovie.get(position), position);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(NowMovieModel nowMovieModel, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
