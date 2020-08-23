package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.base.data.MoviesLocalDatabase;
import com.interview.inshorts.base.views.MovieListViewAdapter;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class UiModule {
    private final MoviesLocalDatabase database;

    public UiModule(Application application) {
        database = Room.databaseBuilder(application.getApplicationContext(),
                MoviesLocalDatabase.class, "Movies.db")
                .build();
    }

    @Provides
    MovieListViewAdapter getMovieListItemAdapter() {
        return new MovieListViewAdapter();
    }
}
