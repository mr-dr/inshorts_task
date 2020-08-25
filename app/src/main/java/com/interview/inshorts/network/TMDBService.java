package com.interview.inshorts.network;

import com.interview.inshorts.base.MovieApiConfig;
import com.interview.inshorts.home.models.NowPlayingResponse;
import com.interview.inshorts.home.models.TrendingResponse;
import com.interview.inshorts.search.models.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {
    // sample api call - https://api.themoviedb.org/3/movie/550?api_key=4e61d28fa5f8e4afdc0bcd33907d020f
    String BASE_URL = "https://api.themoviedb.org/";

    @GET("3/search/movie")
    Observable<SearchResponse> getKeywordSearch(
            @Query("query") String keyword, @Query("page") int page, @Query("api_key") String authKey);

    @GET("3/movie/{movie_id}")
    Observable<MovieApiConfig> getMovieById(@Path("movie_id") int movieId, @Query("api_key") String authKey);

    @GET("3/trending/movie/day")
    Observable<TrendingResponse> getTrendingMovies(@Query("api_key") String authKey);

    @GET("3/movie/now_playing")
    Observable<NowPlayingResponse> getNowPlayingMovies(@Query("page") int page, @Query("api_key") String authKey);
}
