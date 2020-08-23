package com.interview.inshorts.base.views;

import android.view.ViewGroup;
import com.interview.inshorts.base.data.Movies;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class MovieListViewAdapter extends ListAdapter<Movies, MovieListItemVH> {

    public MovieListViewAdapter() {
        super(new MovieListItemDiffUtil());
    }

    @NonNull
    @Override
    public MovieListItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemView view = new MovieListItemView(parent.getContext());
        return new MovieListItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListItemVH holder, int position) {
        holder.updateView(getItem(position));
    }

}
class MovieListItemDiffUtil extends DiffUtil.ItemCallback<Movies> {

    @Override
    public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return areContentsTheSame(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return (oldItem.getId() == newItem.getId());
    }
}
