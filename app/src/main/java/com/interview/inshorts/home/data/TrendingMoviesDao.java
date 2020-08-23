package com.interview.inshorts.home.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TrendingMoviesDao {

//    @Query("SELECT * FROM TrendingMovies LIMIT 1")
//    Flowable<List<TrendingMovies>> getMovies();
    @Query("SELECT * FROM TrendingMovies")
    Flowable<List<TrendingMovies>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertMovies(List<TrendingMovies> movies);

    @Query("DELETE FROM TrendingMovies")
    void deleteAllMovies();
}
