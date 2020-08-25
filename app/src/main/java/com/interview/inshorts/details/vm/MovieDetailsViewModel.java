package com.interview.inshorts.details.vm;

import android.util.Log;

import com.interview.inshorts.AppController;
import com.interview.inshorts.base.TaskManager;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;

import java.util.Arrays;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel extends ViewModel {

    @Inject MovieDetailsRepo mMovieDetailsRepo;

    private MutableLiveData<Boolean> isBookMarkedLiveData;

    public MovieDetailsViewModel() {
        AppController.getInstance().getApplicationComponent().inject(this);
        isBookMarkedLiveData = new MutableLiveData<>();
    }

    public void populateIsBookmarkedData(int id) {
        // can be switched to completable instead of observable
        Disposable disposable = mMovieDetailsRepo.getBookmarksByIds(new int[]{id}).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                Log.d("app", "vm populateIsBookmarkedData() succeeded");
                isBookMarkedLiveData.setValue((response.size() > 0));
            }, throwable -> {
                Log.d("app", "vm populateIsBookmarkedData() failed");
            });
    }

    public LiveData<Boolean> getIsBookmarkedLiveData() {
        return isBookMarkedLiveData;
    }

    public void modifyBookmarks(boolean isBookmarked, Movies movie) {
        if (movie == null) return;
        TaskManager.getInstance().doInBackground(() -> {
            if(isBookmarked) {
                Log.d("app", "removing bookmark for movie: "+ movie.getId());
                mMovieDetailsRepo.deleteBookmarksById(new int[]{movie.getId()});
            } else {
                Log.d("app", "adding bookmark for movie: "+ movie.getId());
                mMovieDetailsRepo.insertBookmarks(Arrays.asList(new BookmarkedMovies(movie)));
            }
        }, null);
    }
}
