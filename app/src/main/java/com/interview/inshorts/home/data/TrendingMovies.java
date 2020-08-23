package com.interview.inshorts.home.data;

import android.os.Parcel;

import com.interview.inshorts.base.data.Movies;
import androidx.room.Entity;

@Entity(tableName = "TrendingMovies")
public class TrendingMovies extends Movies {

    public TrendingMovies(Parcel in) {
        super(in);
    }

    public TrendingMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new TrendingMovies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
