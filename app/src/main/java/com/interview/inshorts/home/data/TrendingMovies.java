package com.interview.inshorts.home.data;

import com.interview.inshorts.base.data.Movies;
import androidx.room.Entity;

@Entity(tableName = "TrendingMovies")
public class TrendingMovies extends Movies {

    public TrendingMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }
}
