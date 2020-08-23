package com.interview.inshorts.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.interview.inshorts.AppController;
import com.interview.inshorts.R;
import com.interview.inshorts.base.BaseActivity;
import com.interview.inshorts.base.views.MovieListView;
import com.interview.inshorts.base.vmFactory.HomeVmFactory;
import com.interview.inshorts.bookmarks.BookmarksActivity;
import com.interview.inshorts.home.vm.HomeViewModel;
import com.interview.inshorts.search.SearchActivity;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private View mSearchBar;
    private View mBookmarksIcon;
    private MovieListView mTrendingMovies;
    private MovieListView mNowPlayingMovies;
    @Inject HomeVmFactory homeVmFactory;
    private HomeViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppController.getInstance().getApplicationComponent().inject(this);
        init();
    }

    private void init() {
        initVm();
        initViews();
        initListeners();
        initVmListeners();
    }

    private void initVm() {
        mViewModel = new ViewModelProvider(this, homeVmFactory).get(HomeViewModel.class);
    }

    private void initViews() {
        mSearchBar = findViewById(R.id.search_bar);
        mBookmarksIcon = findViewById(R.id.bookmark_btn);
        mTrendingMovies = findViewById(R.id.trending_view);
        mNowPlayingMovies = findViewById(R.id.now_playing_view);
    }

    private void initVmListeners() {
        mViewModel.getTrendingMoviesLiveData().observe(this, response -> {
            Log.d("app", "updating trending view:\n"+new Gson().toJson(response));
            mTrendingMovies.updateView(response);
        });
        mViewModel.getNowPlayingMoviesLiveData().observe(this, response -> {
            Log.d("app", "updating now playing view:\n"+new Gson().toJson(response));
            mNowPlayingMovies.updateView(response);
        });
    }

    private void initListeners() {
        mSearchBar.setOnClickListener(this);
        mBookmarksIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == null) return;
        int id = v.getId();
        switch (id) {
            case R.id.search_bar:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.bookmark_btn:
                startActivity(new Intent(this, BookmarksActivity.class));
                break;
        }
    }
}
