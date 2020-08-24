package com.interview.inshorts.bookmarks;

import android.util.Log;

import com.interview.inshorts.AppController;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;

import java.util.List;
import javax.inject.Inject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarksViewModel extends ViewModel {
    @Inject BookmarksRepo repo;
    private MutableLiveData<List<BookmarkedMovies>> liveData = new MutableLiveData<>();

    public BookmarksViewModel() {
        AppController.getInstance().getApplicationComponent().inject(this);
    }

    public void start() {
        Observable<List<BookmarkedMovies>> moviesObservable = repo.getBookmarkedMoviesObservable();
        Disposable disposable = moviesObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                Log.d("app", "bookmarks vm start() succeed");
                liveData.setValue(response);
            }, throwable -> {
                Log.d("app", "bookmarks vm start() failed");
            });
    }

    public LiveData<List<BookmarkedMovies>> getBookmarkedMoviesLiveData() {
        return liveData;
    }
}
