package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NowMovieModel implements Parcelable {
    String poster_path;
    String backdrop_path;
    String release_date;
    String id;
    String title;
    boolean video;

    public NowMovieModel(String backdrop_path, String poster_path, String release_date, String id, String title, boolean video) {
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.title = title;
        this.video = video;
        this.backdrop_path = backdrop_path;
    }

    protected NowMovieModel(Parcel in) {
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        id = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
    }

    public static final Creator<NowMovieModel> CREATOR = new Creator<NowMovieModel>() {
        @Override
        public NowMovieModel createFromParcel(Parcel in) {
            return new NowMovieModel(in);
        }

        @Override
        public NowMovieModel[] newArray(int size) {
            return new NowMovieModel[size];
        }
    };

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(release_date);
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeByte((byte) (video ? 1 : 0));
    }
}
