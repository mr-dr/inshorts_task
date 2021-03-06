package com.interview.inshorts.home.data;

import com.interview.inshorts.AppController;
import com.interview.inshorts.base.MovieApiConfig;
import com.interview.inshorts.home.models.NowPlayingResponse;
import com.interview.inshorts.home.models.TrendingResponse;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeViewRepo implements HomeViewDataSource<List<TrendingMovies>, List<NowPlayingMovies>> {

    @Inject HomeViewLocalDataSource mLocalDataSource;
    @Inject HomeViewRemoteDataSource mRemoteDataSource;

    public HomeViewRepo() {
        AppController.getInstance().getApplicationComponent().inject(this);
        subscribeLocalDataSourceWithRemote();
    }

    @Override
    public Observable<List<TrendingMovies>> getTrendingMoviesObservable() {
        return mLocalDataSource.getTrendingMoviesObservable();
    }

    @Override
    public Observable<List<NowPlayingMovies>> getNowPlayingMoviesMoviesObservable() {
        return mLocalDataSource.getNowPlayingMoviesMoviesObservable();
    }

    @Override
    public void cacheTrendingMovies(List<TrendingMovies> response) {
//        mLocalDataSource.cacheTrendingMovies(response);
    }

    @Override
    public void cacheNowPlayingMovies(List<NowPlayingMovies> response) {
//        mLocalDataSource.cacheNowPlayingMovies(response);
    }

    @Override
    public void loadTrendingMovies() {
        mRemoteDataSource.loadTrendingMovies();
    }

    @Override
    public void loadNowPlayingMovies() {
        mRemoteDataSource.loadNowPlayingMovies();
    }

    private void subscribeLocalDataSourceWithRemote() {
        Observable<TrendingResponse> trendingRemoteObservable =
                mRemoteDataSource.getTrendingMoviesObservable();
        DisposableObserver<TrendingResponse> tendingObserver = trendingRemoteObservable.subscribeWith(new  DisposableObserver<TrendingResponse>() {

            @Override
            public void onNext(TrendingResponse response) {
                Log.d("app", "SUCCESS mTrendingLocalDataSource notified");
                List<MovieApiConfig> apiResults = response.getResults();
                List<TrendingMovies> trendingMovies = convertApiConfigToTrendingConfig(apiResults);
                mLocalDataSource.cacheTrendingMovies(trendingMovies);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("app", "ERR mTrendingLocalDataSource notified");
            }

            @Override
            public void onComplete() {
            }
        });
        Observable<NowPlayingResponse> nowPlayingRemoteObservable =
                mRemoteDataSource.getNowPlayingMoviesMoviesObservable();
        DisposableObserver<NowPlayingResponse> nowPlayingObserver = nowPlayingRemoteObservable.subscribeWith(new  DisposableObserver<NowPlayingResponse>() {

            @Override
            public void onNext(NowPlayingResponse response) {
                Log.d("app", "SUCCESS mNowPlayingLocalDataSource notified");
                List<MovieApiConfig> apiResults = response.getResults();
                List<NowPlayingMovies> nowPlayingMovies = convertApiConfigToNowPlayingConfig(apiResults);
                mLocalDataSource.cacheNowPlayingMovies(nowPlayingMovies);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("app", "ERR mNowPlayingLocalDataSource notified");
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private List<TrendingMovies> convertApiConfigToTrendingConfig(@Nullable List<MovieApiConfig> apiResults) {
        List<TrendingMovies> list = new ArrayList<>();
        if (apiResults == null) return list;
        for(MovieApiConfig apiConfig : apiResults) {
            list.add(new TrendingMovies(apiConfig.getId(), apiConfig.getTitle(), apiConfig.getPosterPath(),
                    apiConfig.getDescription(), apiConfig.getFormattedRating(), apiConfig.isAdult()));
        }
        return list;
    }

    private List<NowPlayingMovies> convertApiConfigToNowPlayingConfig(@Nullable List<MovieApiConfig> apiResults) {
        List<NowPlayingMovies> list = new ArrayList<>();
        if (apiResults == null) return list;
        for(MovieApiConfig apiConfig : apiResults) {
            list.add(new NowPlayingMovies(apiConfig.getId(), apiConfig.getTitle(), apiConfig.getPosterPath(),
                    apiConfig.getDescription(), apiConfig.getFormattedRating(), apiConfig.isAdult()));
        }
        return list;
    }
}
