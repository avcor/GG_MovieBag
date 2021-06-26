package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.NowMovieModel;
import com.example.myapplication.models.ProductionCompanyModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductionHousesAdapter extends RecyclerView.Adapter<ProductionHousesAdapter.Holder> {
    List<ProductionCompanyModel> productionCompanyModels;

    public void setProductionCompanyModels(List<ProductionCompanyModel> productionCompanyModels) {
        this.productionCompanyModels = productionCompanyModels;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.porduction_houses_layout,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        Glide.with(holder.IV.getContext())
                .load("https://image.tmdb.org/t/p/w500"+ productionCompanyModels.get(position).getLogo_path())
                .into(holder.IV);
    }

    @Override
    public int getItemCount() {
        if(productionCompanyModels != null){
            return productionCompanyModels.size();
        }
        else return 0;
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView IV;
        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            IV = itemView.findViewById(R.id.productionHouseIV);
        }
    }
}
