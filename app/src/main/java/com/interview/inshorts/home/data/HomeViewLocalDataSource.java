package com.interview.inshorts.home.data;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import android.util.Log;

import com.interview.inshorts.base.TaskManager;

public class HomeViewLocalDataSource implements HomeViewDataSource<List<TrendingMovies>, List<NowPlayingMovies>> {

    private final TrendingMoviesDao mTrendingDao;
    private final NowPlayingMoviesDao mNowPlayingDao;
    private final Subject<List<TrendingMovies>> trendingObservable;
    private final Subject<List<NowPlayingMovies>> nowPlayingObservable;


    public HomeViewLocalDataSource(
            TrendingMoviesDao trendingDao, NowPlayingMoviesDao nowPlayingDao) {
        mTrendingDao = trendingDao;
        mNowPlayingDao = nowPlayingDao;
        trendingObservable = PublishSubject.create();
        nowPlayingObservable = PublishSubject.create();
    }

    @Override
    public Observable<List<TrendingMovies>> getTrendingMoviesObservable() { // first call
        mTrendingDao.getMovies().toObservable().subscribe(trendingObservable);
        return trendingObservable;
    }

    @Override
    public Observable<List<NowPlayingMovies>> getNowPlayingMoviesMoviesObservable() { // first call
        mNowPlayingDao.getMovies().toObservable().subscribe(nowPlayingObservable);
        return nowPlayingObservable;
    }

    @Override
    public void cacheTrendingMovies(List<TrendingMovies> response) { // subsequent call
        Log.d("app", "starting cacheTrendingMovies");

        TaskManager.getInstance().doInBackground(() -> {
            mTrendingDao.deleteAllMovies();
            List<Long> longs = mTrendingDao.insertMovies(response);
            Log.d("app", "inserted longs: " + longs);
        }, null);
        trendingObservable.onNext(response);
    }

    @Override
    public void cacheNowPlayingMovies(List<NowPlayingMovies> response) { // subsequent call
        Log.d("app", "starting cacheNowPlayingMovies");
        TaskManager.getInstance().doInBackground(() -> {
            mNowPlayingDao.deleteAllMovies();
            List<Long> longs = mNowPlayingDao.insertMovies(response);
            Log.d("app", "inserted longs: " + longs);
        }, null);
        nowPlayingObservable.onNext(response);
    }

    @Override
    public void loadTrendingMovies() {
        // do nothing
    }

    @Override
    public void loadNowPlayingMovies() {
        // do nothing
    }
}
