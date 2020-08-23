package com.interview.inshorts.bookmarks.data;

import com.interview.inshorts.home.data.TrendingMovies;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BookmarkedMoviesDao {

//    @Query("SELECT * FROM TrendingMovies LIMIT 1")
    @Query("SELECT * FROM BookmarkedMovies")
    Flowable<TrendingMovies> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertMovies(List<BookmarkedMovies> movies);

    @Query("DELETE FROM BookmarkedMovies")
    void deleteAllMovies();
}
