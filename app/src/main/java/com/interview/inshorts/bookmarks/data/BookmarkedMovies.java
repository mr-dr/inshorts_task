package com.interview.inshorts.bookmarks.data;

import android.os.Parcel;

import com.interview.inshorts.base.data.Movies;

import androidx.room.Entity;

@Entity(tableName = "BookmarkedMovies")
public class BookmarkedMovies extends Movies {

    public BookmarkedMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }

    public BookmarkedMovies(Movies movie) {
        super(movie.getId(), movie.getTitle(), movie.getPosterPath(), movie.getDescription(), movie.getRating(), movie.isAdult());
    }

    public BookmarkedMovies(Parcel in) {
        super(in);
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new BookmarkedMovies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
