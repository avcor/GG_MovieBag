package com.example.myapplication.adapters;

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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.Holder> {

    private List<CastModel> mCast;

    public  CastRecyclerAdapter(){}

    public void setmCast(List<CastModel> mCast){
        this.mCast = mCast;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_recycler_layout,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.nameTV.setText(mCast.get(position).getName());
        holder.DepartmentTV.setText(mCast.get(position).getKnown_for_department());
        holder.CharacterTV.setText(mCast.get(position).getCharacter());

        Glide.with(holder.castIV.getContext())
                .load("https://image.tmdb.org/t/p/w500"+ mCast.get(position).getProfile_path())
                .into(holder.castIV);
    }
    @Override
    public int getItemCount() {
        return mCast.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView castIV;
        TextView nameTV, DepartmentTV, CharacterTV;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            castIV = itemView.findViewById(R.id.castImageView);
            nameTV = itemView.findViewById(R.id.castNameTV);
            DepartmentTV = itemView.findViewById(R.id.castDepartmentTV);
            CharacterTV = itemView.findViewById(R.id.castCharacterTV);
        }
    }
}
