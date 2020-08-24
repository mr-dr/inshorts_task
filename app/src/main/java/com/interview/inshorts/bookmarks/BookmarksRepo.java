package com.interview.inshorts.bookmarks;

import android.util.Log;

import com.interview.inshorts.AppController;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;

public class BookmarksRepo {
    @Inject BookmarksDao mDao;
    private MutableLiveData<Boolean> isBookmarkedLivedata;

    public BookmarksRepo() {
        AppController.getInstance().getApplicationComponent().inject(this);;
        isBookmarkedLivedata = new MutableLiveData<>();
    }

    public Observable<List<BookmarkedMovies>> getBookmarkedMoviesObservable() {
        return mDao.getMovies().toObservable();
    }
}
