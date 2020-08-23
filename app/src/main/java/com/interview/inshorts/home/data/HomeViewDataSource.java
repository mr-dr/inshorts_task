package com.interview.inshorts.home.data;

import io.reactivex.Observable;

public interface HomeViewDataSource<T, N> {
    Observable<T> getTrendingMoviesObservable();
    void loadTrendingMovies();
    void cacheTrendingMovies(T response);
    Observable<N> getNowPlayingMoviesMoviesObservable();
    void cacheNowPlayingMovies(N response);
    void loadNowPlayingMovies();
}
