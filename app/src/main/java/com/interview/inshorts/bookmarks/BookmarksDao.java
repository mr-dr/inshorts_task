package com.interview.inshorts.bookmarks;

import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface BookmarksDao {

    @Query("SELECT * FROM BookmarkedMovies")
    Flowable<List<BookmarkedMovies>> getMovies();

    @Query("SELECT * FROM BookmarkedMovies WHERE id IN(:ids)")
    Flowable<List<BookmarkedMovies>> getMovieByIds(int[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertMovies(List<BookmarkedMovies> movies);

    @Query("DELETE FROM BookmarkedMovies")
    void deleteAllMovies();

    @Query("DELETE FROM BookmarkedMovies WHERE id IN(:ids)")
    void deleteMoviesById(int[] ids);

}
