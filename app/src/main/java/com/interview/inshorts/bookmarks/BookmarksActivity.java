package com.interview.inshorts.bookmarks;

import android.os.Bundle;

import com.interview.inshorts.R;
import com.interview.inshorts.base.BaseActivity;
import com.interview.inshorts.base.views.MovieListView;

import androidx.lifecycle.ViewModelProvider;

public class BookmarksActivity extends BaseActivity {

    private MovieListView mMoviesList;
    private BookmarksViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        init();
    }

    private void init() {
        initViews();
        updateView();
    }

    private void initViews() {
        mMoviesList = findViewById(R.id.movies_list);
        mViewModel = new ViewModelProvider(this).get(BookmarksViewModel.class);
    }

    private void updateView() {
        mViewModel.start();
        mViewModel.getBookmarkedMoviesLiveData().observe(this, response -> {
           mMoviesList.updateView(response);
        });
    }
}
