package com.interview.inshorts.search.data;

import android.util.Log;
import com.interview.inshorts.network.ApiListener;
import com.interview.inshorts.network.ApiRequest;
import com.interview.inshorts.network.Constants;
import com.interview.inshorts.network.TMDBService;
import com.interview.inshorts.search.models.SearchResponse;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class SearchRepo {
    private Subject<SearchResponse> resultsObservable;

    public SearchRepo() {
        resultsObservable = PublishSubject.create();
    }

    public Observable<SearchResponse> getResultsObservable() {
        return resultsObservable;
    }

    public void loadResults(String searchText, int page) {
        ApiListener<SearchResponse> l = new ApiListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse response) {
                Log.d("app", "search movies call succeeded");
                resultsObservable.onNext(response);
            }

            @Override
            public void onError(Throwable error) {
                Log.d("app", "search movies call failed");
            }
        };
        ApiRequest<SearchResponse> request = new ApiRequest<SearchResponse>(l) {
            @Override
            protected io.reactivex.Observable<SearchResponse> getObservable(TMDBService service) {
                Log.d("app", "searching for : " + searchText + ", " + page);
                return service.getKeywordSearch(searchText, page, Constants.API_V3_AUTH);
            }
        };
        request.start();
    }
}
