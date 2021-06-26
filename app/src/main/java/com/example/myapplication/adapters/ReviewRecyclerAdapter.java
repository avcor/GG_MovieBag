package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.CastModel;
import com.example.myapplication.models.ReviewModel;
import com.mikhaellopez.circularimageview.CircularImageView;
//import com.skyhope.showmoretextview.ShowMoreTextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.Holder> {

    List<ReviewModel> mReview;

    public void setmReview(List<ReviewModel> mReview){
        this.mReview = mReview;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_recycler_layout,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.authorName.setText(mReview.get(position).getAuthor());
        holder.reviewRating.setRating(mReview.get(position).getAuthor_details().getRating()/2);
        holder.reviewDate.setText(mReview.get(position).getCreated_at().substring(0,10));
        holder.reviewContent.setText(mReview.get(position).getContent());
        Glide.with(holder.authorImage.getContext())
                .load(mReview.get(position).getAuthor_details().getAvatar_path().substring(1))
                .into(holder.authorImage);
    }

    @Override
    public int getItemCount() {
        return mReview.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        CircularImageView authorImage;
        TextView authorName, reviewDate, reviewContent;
        RatingBar reviewRating;
//        ShowMoreTextView content;
        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            authorImage = itemView.findViewById(R.id.authorIV);
            authorName = itemView.findViewById(R.id.authorNameTV);
            reviewDate = itemView.findViewById(R.id.reviewDateTV);
//            content = itemView.findViewById(R.id.content);
            reviewRating = itemView.findViewById(R.id.reviewRating);
            reviewContent = itemView.findViewById(R.id.reviewContent);
        }
    }
}
