package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.base.data.MoviesLocalDatabase;
import com.interview.inshorts.base.views.MovieListViewAdapter;
import com.interview.inshorts.network.Constants;
import com.interview.inshorts.search.adapters.SearchResultsAdapter;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class UiModule {
    private final MoviesLocalDatabase database;

    public UiModule(Application application) {
        database = Room.databaseBuilder(application.getApplicationContext(),
                MoviesLocalDatabase.class, Constants.LOCAL_DB_NAME)
                .build();
    }

    @Provides
    MovieListViewAdapter getMovieListItemAdapter() {
        return new MovieListViewAdapter();
    }

    @Provides
    SearchResultsAdapter getSearchResultsAdapter() {
        return new SearchResultsAdapter();
    }
}
