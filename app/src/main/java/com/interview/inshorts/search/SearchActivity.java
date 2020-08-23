package com.interview.inshorts.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.interview.inshorts.R;
import com.interview.inshorts.base.BaseActivity;
import com.interview.inshorts.network.ApiListener;
import com.interview.inshorts.network.ApiRequest;
import com.interview.inshorts.network.Constants;
import com.interview.inshorts.network.TMDBService;
import com.interview.inshorts.search.models.SearchResponse;

import io.reactivex.Observable;

public class SearchActivity extends BaseActivity {

//    private View mSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
        init();
    }

    private void init() {
        initViews();
        initListeners();
//        makeSearchCall();
    }

    private void makeSearchCall() { // fixme move to vm
        ApiListener<SearchResponse> l = new ApiListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse response) {

            }

            @Override
            public void onError(Throwable error) {

            }
        };
        ApiRequest<SearchResponse> request = new ApiRequest<SearchResponse>(l) {
            @Override
            protected Observable<SearchResponse> getObservable(TMDBService service) {
                return service.getKeywordSearch("two", 1, Constants.API_V3_AUTH);
            }
        };
        request.start();
    }

    private void initViews() {
    }

    private void initListeners() {
    }

}
