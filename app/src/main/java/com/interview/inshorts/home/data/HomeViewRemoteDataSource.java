package com.interview.inshorts.home.data;

import com.interview.inshorts.home.models.NowPlayingResponse;
import com.interview.inshorts.home.models.TrendingResponse;
import com.interview.inshorts.network.ApiListener;
import com.interview.inshorts.network.ApiRequest;
import com.interview.inshorts.network.Constants;
import com.interview.inshorts.network.TMDBService;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import android.util.Log;

public class HomeViewRemoteDataSource implements HomeViewDataSource<TrendingResponse, NowPlayingResponse> {

//    Observable<TrendingResponse> trendingMoviesObservable;
    private Subject<TrendingResponse> trendingMoviesObservable;
    private Subject<NowPlayingResponse> nowPlayingMoviesObservable;

    public HomeViewRemoteDataSource() {
        trendingMoviesObservable = PublishSubject.create();
        nowPlayingMoviesObservable = PublishSubject.create();
    }

    @Override
    public Observable<TrendingResponse> getTrendingMoviesObservable() {
        return trendingMoviesObservable;
    }

    @Override
    public void loadTrendingMovies() {
        ApiListener<TrendingResponse> l = new ApiListener<TrendingResponse>() {
            @Override
            public void onResponse(TrendingResponse response) {
                Log.d("app", "trending movies call succeeded");
                trendingMoviesObservable.onNext(response);
            }

            @Override
            public void onError(Throwable error) {
                Log.d("app", "trending movies call failed");
            }
        };
        ApiRequest<TrendingResponse> request = new ApiRequest<TrendingResponse>(l) {
            @Override
            protected Observable<TrendingResponse> getObservable(TMDBService service) {
                return service.getTrendingMovies(Constants.API_V3_AUTH);
            }
        };
        request.start();
    }

    @Override
    public void cacheTrendingMovies(TrendingResponse response) {
        // do nothing
    }

    @Override
    public Observable<NowPlayingResponse> getNowPlayingMoviesMoviesObservable() {
        return nowPlayingMoviesObservable;
    }

    @Override
    public void cacheNowPlayingMovies(NowPlayingResponse response) {
        // do nothing
    }

    @Override
    public void loadNowPlayingMovies() {
        ApiListener<NowPlayingResponse> l = new ApiListener<NowPlayingResponse>() {
            @Override
            public void onResponse(NowPlayingResponse response) {
                Log.d("app", "now playing movies call succeeded");
                nowPlayingMoviesObservable.onNext(response);
            }

            @Override
            public void onError(Throwable error) {
                Log.d("app", "now playing movies call failed");
            }
        };
        ApiRequest<NowPlayingResponse> request = new ApiRequest<NowPlayingResponse>(l) {
            @Override
            protected Observable<NowPlayingResponse> getObservable(TMDBService service) {
                // pagination not requested
                return service.getNowPlayingMovies(1, Constants.API_V3_AUTH);
            }
        };
        request.start();
    }
}
