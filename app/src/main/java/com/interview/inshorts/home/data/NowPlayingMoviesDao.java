package com.interview.inshorts.home.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface NowPlayingMoviesDao {

//    @Query("SELECT * FROM TrendingMovies LIMIT 1")
    @Query("SELECT * FROM NowPlayingMovies")
    Flowable<List<NowPlayingMovies>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertMovies(List<NowPlayingMovies> movies);

    @Query("DELETE FROM NowPlayingMovies")
    void deleteAllMovies();
}
