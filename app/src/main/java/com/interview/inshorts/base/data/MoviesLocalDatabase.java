package com.interview.inshorts.base.data;

import android.content.Context;

import com.interview.inshorts.base.MovieApiConfig;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;
import com.interview.inshorts.home.data.NowPlayingMovies;
import com.interview.inshorts.home.data.NowPlayingMoviesDao;
import com.interview.inshorts.home.data.TrendingMovies;
import com.interview.inshorts.home.data.TrendingMoviesDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TrendingMovies.class, NowPlayingMovies.class, BookmarkedMovies.class}, version = 1, exportSchema = false)
public abstract class MoviesLocalDatabase extends RoomDatabase {
    private static volatile MoviesLocalDatabase INSTANCE;
    private final static String dbName = "TrendingMovies.db";

    public abstract TrendingMoviesDao trendingMoviesDao();

    public abstract NowPlayingMoviesDao nowPlayingMoviesDao();

    public static MoviesLocalDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesLocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesLocalDatabase.class, dbName)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
