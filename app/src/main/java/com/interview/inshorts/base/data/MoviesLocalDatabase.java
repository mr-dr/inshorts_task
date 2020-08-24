package com.interview.inshorts.base.data;

import com.interview.inshorts.bookmarks.BookmarksDao;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;
import com.interview.inshorts.home.data.NowPlayingMovies;
import com.interview.inshorts.home.data.NowPlayingMoviesDao;
import com.interview.inshorts.home.data.TrendingMovies;
import com.interview.inshorts.home.data.TrendingMoviesDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TrendingMovies.class, NowPlayingMovies.class, BookmarkedMovies.class}, version = 1, exportSchema = false)
public abstract class MoviesLocalDatabase extends RoomDatabase {

    public abstract TrendingMoviesDao trendingMoviesDao();

    public abstract NowPlayingMoviesDao nowPlayingMoviesDao();

    public abstract BookmarksDao bookmarkedMoviesDao();
}
