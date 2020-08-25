package com.interview.inshorts.search.vh;

import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.search.listeners.SearchItemClickListener;
import com.interview.inshorts.search.views.SearchItemView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

public class SearchItemVH extends RecyclerView.ViewHolder {
    public SearchItemVH(@NonNull SearchItemView itemView) {
        super(itemView);
    }

    public void updateView(Movies config, SearchItemClickListener listener) {
        ((SearchItemView) itemView).updateView(config);
        itemView.setOnClickListener(v -> {
            Log.d("app", "opening movie details for : " + config.getTitle() + "(" + config.getId() + ")");
            listener.onSearchItemClicked(itemView.getContext(), config);
        });
    }
}
