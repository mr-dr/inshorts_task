package com.interview.inshorts.search.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.base.utils.BundleExtraKeys;
import com.interview.inshorts.details.activity.MovieDetailsActivity;
import com.interview.inshorts.search.listeners.SearchItemClickListener;
import com.interview.inshorts.search.vh.SearchItemVH;
import com.interview.inshorts.search.views.SearchItemView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class SearchResultsAdapter extends ListAdapter<Movies, SearchItemVH> implements SearchItemClickListener {

    public SearchResultsAdapter() {
        super(new SearchListDiffUtil());
    }

    @NonNull
    @Override
    public SearchItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchItemView view = new SearchItemView(parent.getContext());
        return new SearchItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemVH holder, int position) {
        Movies config = getItem(position);
        holder.updateView(config, this);
    }

    @Override
    public void onSearchItemClicked(@NonNull Context context, @NonNull Movies movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(BundleExtraKeys.MOVIE_CONFIG, movie);
        context.startActivity(intent);
    }
}

class SearchListDiffUtil extends DiffUtil.ItemCallback<Movies> {

    @Override
    public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return areContentsTheSame(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
