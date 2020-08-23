package com.interview.inshorts.home.data;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

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

    @Override // fixme move to bg thread
    public void cacheTrendingMovies(List<TrendingMovies> response) { // subsequent call
        Single.fromCallable(() -> {
            mTrendingDao.deleteAllMovies();
            mTrendingDao.insertMovies(response);
            return null;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()); // fixme check if this can be removed
        trendingObservable.onNext(response);
//        List<MovieApiConfig> apiResults = response.getResults();
//        List<TrendingMovies> trendingMovies = convertApiConfigToTrendingConfig(apiResults);
//        mTrendingDao.insertMovies(trendingMovies);
    }

    @Override // fixme move to bg thread
    public void cacheNowPlayingMovies(List<NowPlayingMovies> response) { // subsequent call
        Single.fromCallable(() -> {
            mNowPlayingDao.deleteAllMovies();
            mNowPlayingDao.insertMovies(response);
            return null;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        nowPlayingObservable.onNext(response);
//        List<MovieApiConfig> apiResults = response.getResults();
//        List<NowPlayingMovies> nowPlayingMovies = convertApiConfigToNowPlayingConfig(apiResults);
//        mNowPlayingDao.insertMovies(nowPlayingMovies);
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
