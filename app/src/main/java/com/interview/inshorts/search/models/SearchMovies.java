package com.interview.inshorts.search.models;

import android.os.Parcel;

import com.interview.inshorts.base.data.Movies;

public class SearchMovies extends Movies {
    public SearchMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }

    protected SearchMovies(Parcel in) {
        super(in);
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new SearchMovies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
