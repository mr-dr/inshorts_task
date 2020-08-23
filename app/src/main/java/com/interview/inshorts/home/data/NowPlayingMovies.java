package com.interview.inshorts.home.data;

import android.os.Parcel;

import com.interview.inshorts.base.data.Movies;

import androidx.room.Entity;

@Entity(tableName = "NowPlayingMovies")
public class NowPlayingMovies extends Movies {

    public NowPlayingMovies(Parcel in) {
        super(in);
    }

    public NowPlayingMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new NowPlayingMovies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
