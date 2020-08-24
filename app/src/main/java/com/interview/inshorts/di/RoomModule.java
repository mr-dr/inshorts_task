package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.base.data.MoviesLocalDatabase;
import com.interview.inshorts.base.vmFactory.HomeVmFactory;
import com.interview.inshorts.bookmarks.BookmarksDao;
import com.interview.inshorts.bookmarks.BookmarksRepo;
import com.interview.inshorts.details.vm.MovieDetailsRepo;
import com.interview.inshorts.home.data.HomeViewDataSource;
import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.data.HomeViewRepo;
import com.interview.inshorts.home.data.NowPlayingMovies;
import com.interview.inshorts.home.data.NowPlayingMoviesDao;
import com.interview.inshorts.home.data.TrendingMovies;
import com.interview.inshorts.home.data.TrendingMoviesDao;

import java.util.List;

import androidx.room.Room;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private final MoviesLocalDatabase database;

    public RoomModule(Application application) {
        database = Room.databaseBuilder(application.getApplicationContext(),
                MoviesLocalDatabase.class, "Movies.db")
                .build();
    }

    @Singleton
    @Provides
    HomeViewDataSource<List<TrendingMovies>, List<NowPlayingMovies>> getHomeViewRepository() {
        return new HomeViewRepo();
    }

    @Singleton
    @Provides
    HomeViewLocalDataSource getHomeViewLocalDataSource() {
        return new HomeViewLocalDataSource();
    }

    @Singleton
    @Provides
    HomeViewRemoteDataSource getHomeViewRemoteDataSource() {
        return new HomeViewRemoteDataSource();
    }

    @Singleton
    @Provides
    HomeVmFactory getHomeViewModelFactory() {
        return new HomeVmFactory();
    }

    @Singleton
    @Provides
    TrendingMoviesDao getTrendingMoviesDao() {
        return database.trendingMoviesDao();
    }

    @Singleton
    @Provides
    NowPlayingMoviesDao getNowPlayingMoviesDao() {
        return database.nowPlayingMoviesDao();
    }

    @Singleton
    @Provides
    BookmarksDao getBookmarkedMoviesDao() {
        return database.bookmarkedMoviesDao();
    }

    @Singleton
    @Provides
    BookmarksRepo getBookmarksRepository() {
        return new BookmarksRepo();
    }

    @Singleton
    @Provides
    MovieDetailsRepo getMovieDetailsRepository() {
        return new MovieDetailsRepo();
    }

}
