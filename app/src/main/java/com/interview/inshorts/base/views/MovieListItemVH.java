package com.interview.inshorts.base.views;

import com.interview.inshorts.base.data.Movies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MovieListItemVH extends RecyclerView.ViewHolder {
    MovieListItemView mItemView;

    public MovieListItemVH(@NonNull MovieListItemView itemView) {
        super(itemView);
        mItemView = itemView;
    }

    public void updateView(Movies itemConfig) {
        mItemView.updateView(itemConfig);
    }
}
