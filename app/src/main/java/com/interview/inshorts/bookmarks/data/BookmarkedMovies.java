package com.interview.inshorts.bookmarks.data;

import com.interview.inshorts.base.data.Movies;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "BookmarkedMovies")
public class BookmarkedMovies extends Movies {

    public BookmarkedMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }
}
