package com.interview.inshorts.base.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.interview.inshorts.R;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.base.utils.BundleExtraKeys;
import com.interview.inshorts.base.utils.MovieInfoHelper;
import com.interview.inshorts.details.activity.MovieDetailsActivity;
import com.interview.inshorts.network.Constants;

import androidx.constraintlayout.widget.ConstraintLayout;

class MovieListItemView extends ConstraintLayout implements View.OnClickListener {

    private int MAX_IMAGES_PER_ROW = 2;
    private double HEIGHT_TO_WIDTH = 1.5;
    private double IMAGE_TO_VIEWPORT = 0.80;

    private ImageView mImage;
    private TextView mTitle;
    private Movies mConfig;

    public MovieListItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.movie_item_view, this, true);
        mImage = findViewById(R.id.image_view);
        mTitle = findViewById(R.id.tv);
        applyDimensions();
    }

    private void applyDimensions() {
        int dimension = (int) (IMAGE_TO_VIEWPORT * (getViewportWidth() / MAX_IMAGES_PER_ROW));
        int margin  = (int) (dimension / (IMAGE_TO_VIEWPORT * 100));
        LayoutParams lp = new LayoutParams(dimension, (int) (dimension * HEIGHT_TO_WIDTH));
        lp.setMargins(margin, margin, margin, margin);
        setLayoutParams(lp);
    }

    public void  updateView(Movies itemConfig) {
        mConfig = itemConfig;
        String title = itemConfig.getTitle();
        String imageUrl = MovieInfoHelper.getImageUrl(itemConfig.getPosterPath());
        Log.d("app", "loading itemConfig: " + title + ", " + imageUrl);
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .transform(new RoundedCorners(30))
                .error(R.drawable.error)
                .fallback(R.drawable.placeholder)
                .into(mImage);
        mTitle.setText(title);
        setOnClickListener(this);
    }

    public int getViewportWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public void onClick(View v) {
        if (v == null) return;
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(BundleExtraKeys.MOVIE_CONFIG, mConfig);
        getContext().startActivity(intent);
    }
}
