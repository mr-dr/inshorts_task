package com.interview.inshorts.search.listeners;

import android.content.Context;
import com.interview.inshorts.base.data.Movies;

public interface SearchItemClickListener {
    void onSearchItemClicked(Context context, Movies movie);
}
