package com.interview.inshorts.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import com.interview.inshorts.AppController;
import com.interview.inshorts.R;
import com.interview.inshorts.base.BaseActivity;
import com.interview.inshorts.base.listeners.PaginationScrollListener;
import com.interview.inshorts.base.listeners.TypeAheadListener;
import com.interview.inshorts.search.adapters.SearchResultsAdapter;
import com.interview.inshorts.search.vm.SearchViewModel;
import javax.inject.Inject;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

public class SearchActivity extends BaseActivity {

    private EditText mSearchBar;
    private RecyclerView mSearchResultList;
    private SearchViewModel mViewModel;
    @Inject SearchResultsAdapter mSearchAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private final long DEBOUNCE_WAIT_TIME = 200L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AppController.getInstance().getApplicationComponent().inject(this);
        init();
    }

    private void init() {
        initViews();
        initListeners();
    }

    private void initViews() {
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSearchBar = findViewById(R.id.search_bar);
        mSearchResultList = findViewById(R.id.search_results);
        mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mSearchResultList.setLayoutManager(mLinearLayoutManager);
        mSearchResultList.setAdapter(mSearchAdapter);
    }

    private void initListeners() {
        mSearchResultList.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                mViewModel.fetchNextPageResults();
            }

            @Override
            protected int totalPageCount() {
                return mViewModel.getTotalPageCount();
            }

            @Override
            protected int populatedItemCount() {
                return mViewModel.getPopulatedItemCount();
            }

            @Override
            protected boolean isLastPage() {
                return mViewModel.getNextPageCount() > mViewModel.getTotalPageCount();
            }

            @Override
            protected boolean isLoading() {
                return mViewModel.isLoading();
            }
        });
        mViewModel.getResultsLiveData().observe(this, results -> {
            String msg = "Empty results received";
            if(results.size() > 0) {
                msg = "populating search view with " + results.size() + " items " + results.get(0).getTitle() + ":" + results.get(results.size() - 1).getTitle();
            }
            Log.d("app", msg);
            mSearchAdapter.submitList(results);
        });
        mSearchBar.addTextChangedListener(new TypeAheadListener(DEBOUNCE_WAIT_TIME) {
            @Override
            public void submitText(@Nullable String text) {
                if (TextUtils.isEmpty(text)) return;
                Log.d("app", "searching for " + text + " at " + System.currentTimeMillis());
                mViewModel.fetchFirstPageResults(text);
            }
        });
    }

}
