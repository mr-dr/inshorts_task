package com.interview.inshorts.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.interview.inshorts.AppController;
import com.interview.inshorts.R;
import com.interview.inshorts.base.data.Movies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListView extends RecyclerView {
    @Inject
    MovieListViewAdapter mAdapter;
    private LayoutManager layoutManager;

    public MovieListView(@NonNull Context context) {
        this(context, null);
    }

    public MovieListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AppController.getInstance().getApplicationComponent().inject(this);
        initAttrs(context, attrs, defStyleAttr);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MovieList,
                defStyleAttr, 0);
        int listType = a.getInt(R.styleable.MovieList_listType, 1);
        switch (listType) {
            case 0:
                layoutManager = new GridLayoutManager(context, 2);
                break;
            default:
                layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                break;

        }
    }

    private void init() {
        setLayoutManager(layoutManager);
        setAdapter(mAdapter);
    }

    public void updateView(List<? extends Movies> response) {
        // received list needs to be converted due to java's constraints
        List<Movies> list = new ArrayList<>();
        list.addAll(response);
        mAdapter.submitList(list);
    }
}
