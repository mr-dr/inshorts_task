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

    @Inject MovieDetailsRepo mViewModel;

    private MutableLiveData<Movies> configLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isBookMarkedLiveData = new MutableLiveData<>();

    public MovieDetailsViewModel() {
        AppController.getInstance().getApplicationComponent().inject(this);
    }

    public void populateMovieLiveData(int id) {
        // TODO("implement")
    }
    public LiveData<Movies> getMovieLiveData() {
        return configLiveData;
    }

    public void populateIsBookmarkedData(int id) {
        // can be switched to completable instead of observable
        Disposable disposable = mViewModel.getBookmarksByIds(new int[]{id}).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                Log.d("app", "vm populateIsBookmarkedData() succeded");
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
                mViewModel.deleteBookmarksById(new int[]{movie.getId()});
            } else {
                Log.d("app", "adding bookmark for movie: "+ movie.getId());
                mViewModel.insertBookmarks(Arrays.asList(new BookmarkedMovies(movie)));
            }
        }, null);
    }
}
