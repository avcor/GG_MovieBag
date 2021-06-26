package com.example.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewModel {
    String author;
    String content;
    String created_at;

    @SerializedName("author_details")
    @Expose
    Author_details author_details;

    public ReviewModel(String author, Author_details author_details, String content, String created_at) {
        this.author = author;
        this.author_details = author_details;
        this.content = content;
        this.created_at = created_at;
    }


    public String getCreated_at() {
        return created_at;
    }

    public String getAuthor() {
        return author;
    }

    public Author_details getAuthor_details() {
        return author_details;
    }

    public String getContent() {
        return content;
    }

    public class Author_details {
        String avatar_path;
        int rating;

        public Author_details(String avatar_path, int rating) {
            this.avatar_path = avatar_path;
            this.rating = rating;
        }

        public int getRating() {
            return rating;
        }

        public String getAvatar_path() {
            return avatar_path;
        }
    }

}

