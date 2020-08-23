package com.interview.inshorts.base.views;

import android.content.Context;
import android.util.AttributeSet;

import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.home.data.TrendingMovies;
import com.interview.inshorts.home.models.TrendingResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListView extends RecyclerView {
    public MovieListView(@NonNull Context context) {
        this(context, null);
    }

    public MovieListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateView(List<? extends Movies> response) {

    }
}
