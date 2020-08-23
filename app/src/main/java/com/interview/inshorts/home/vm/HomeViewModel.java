package com.interview.inshorts.home.vm;

import android.util.Log;

import com.google.gson.Gson;
import com.interview.inshorts.home.data.HomeViewDataSource;
import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.data.HomeViewRepo;
import com.interview.inshorts.home.data.NowPlayingMovies;
import com.interview.inshorts.home.data.TrendingMovies;
import com.interview.inshorts.home.models.NowPlayingResponse;
import com.interview.inshorts.home.models.TrendingResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private HomeViewDataSource repo;
    private MutableLiveData<List<TrendingMovies>> trendingLiveData;
    private MutableLiveData<List<NowPlayingMovies>> nowPlayingLiveData;

    public HomeViewModel(HomeViewLocalDataSource localData, HomeViewRemoteDataSource remoteData) {
        repo = new HomeViewRepo(localData, remoteData);
        trendingLiveData = new MutableLiveData<>();
        nowPlayingLiveData = new MutableLiveData<>();
        initDataListeners();
        fetchResponse();
    }

    public LiveData<List<TrendingMovies>> getTrendingMoviesLiveData() {
        return trendingLiveData;
    }

    public LiveData<List<NowPlayingMovies>> getNowPlayingMoviesLiveData() {
        return nowPlayingLiveData;
    }

    private void initDataListeners() {
        Observable<List<TrendingMovies>> trendingMoviesObservable = repo.getTrendingMoviesObservable();
        Observable<List<NowPlayingMovies>> nowPlayingMoviesObservable = repo.getNowPlayingMoviesMoviesObservable();
        Disposable trendingDisposable = trendingMoviesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("app", "");
                    trendingLiveData.setValue(response);
                }, throwable -> {
                    Log.d("app", "");
                });
        Disposable nowPlayingDisposable = nowPlayingMoviesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("app", "");
                    nowPlayingLiveData.setValue(response);
                }, throwable -> {
                    Log.d("app", "");
                });
    }

    private void fetchResponse() {
        repo.loadTrendingMovies();
        repo.loadNowPlayingMovies();
    }
}
