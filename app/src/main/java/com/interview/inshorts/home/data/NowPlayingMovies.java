package com.interview.inshorts.home.data;

import com.interview.inshorts.base.data.Movies;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "NowPlayingMovies")
public class NowPlayingMovies extends Movies {

    public NowPlayingMovies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        super(id, title, posterPath, description, rating, isAdult);
    }
}
