package com.interview.inshorts.details.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.interview.inshorts.R;
import com.interview.inshorts.base.BaseActivity;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.base.utils.BundleExtraKeys;
import com.interview.inshorts.base.utils.MovieInfoHelper;
import com.interview.inshorts.details.vm.MovieDetailsViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieDetailsActivity extends BaseActivity implements View.OnClickListener {

    private MovieDetailsViewModel mViewModel;
    private int mMovieId = -1;
    private Movies mMovieConfig;

    private ImageView mPoster;
    private TextView mTitle;
    private TextView mRating;
    private TextView mDescription;
    private TextView mBookmarkToggle;
    private boolean isBookMarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        init();
    }

    private void init() {
        initViews();
        populateView();
    }

    private void populateBookmarkView(int id) {
        mViewModel.populateIsBookmarkedData(id);
        mViewModel.getIsBookmarkedLiveData().observe(this, isBookmarked -> setBookmarked(isBookmarked));
    }

    private void setBookmarked(boolean isBookmarked) {
        this.isBookMarked = isBookmarked;
        setBookmarkedText();
        mBookmarkToggle.setVisibility(View.VISIBLE);
        mBookmarkToggle.setOnClickListener(this);
    }

    private void setBookmarkedText() {
        String bookmarkString = isBookMarked ? getString(R.string.remove_bookmark) : getString(R.string.add_bookmark);
        mBookmarkToggle.setText(bookmarkString);
    }

    private void initViews() {
        mPoster = findViewById(R.id.image_view);
        mTitle = findViewById(R.id.title);
        mRating = findViewById(R.id.rating);
        mDescription = findViewById(R.id.description);
        mBookmarkToggle = findViewById(R.id.bookmark_tv);
    }

    private void populateView() {
        mMovieId = getIntent().getIntExtra(BundleExtraKeys.MOVIE_ID, -1);
        mMovieConfig = getIntent().getParcelableExtra(BundleExtraKeys.MOVIE_CONFIG);
        if(mMovieConfig != null) {
            populateBookmarkView(mMovieConfig.getId());
            loadPage(mMovieConfig);
        } else if (mMovieId > 0) {
            populateBookmarkView(mMovieId);
            mViewModel.populateMovieLiveData(mMovieId);
            mViewModel.getMovieLiveData().observe(this, movieConfig1 -> loadPage(movieConfig1));
        } else {
            Toast.makeText(this, getString(R.string.base_error), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadPage(Movies movieConfig) {
        mTitle.setText(movieConfig.getTitle());
        mRating.setText(movieConfig.getRating());
        mDescription.setText(movieConfig.getDescription());
        Glide.with(this)
                .load(MovieInfoHelper.getImageUrl(movieConfig.getPosterPath()))
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .transform(new RoundedCorners(30))
                .error(R.drawable.error)
                .fallback(R.drawable.placeholder)
                .into(mPoster);
    }

    @Override
    public void onClick(View v) {
        if(v == null) return;
        int id = v.getId();
        switch (id) {
            case R.id.bookmark_tv:
                mViewModel.modifyBookmarks(isBookMarked, mMovieConfig);
                isBookMarked = !isBookMarked;
                setBookmarkedText();
                break;
        }
    }
}
