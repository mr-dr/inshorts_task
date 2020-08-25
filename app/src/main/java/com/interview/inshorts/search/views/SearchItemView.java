package com.interview.inshorts.search.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.interview.inshorts.R;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.base.views.BaseTextView;
import com.interview.inshorts.search.listeners.SearchItemClickListener;

import androidx.annotation.NonNull;

public class SearchItemView extends BaseTextView {

    private final int STROKE_COLOR = getContext().getResources().getColor(R.color.black);
    private final int STROKE_WIDTH = 2;
    private final int PADDING = 30;
    private final int TEXT_SIZE_IN_SP = 14;
    private SearchItemClickListener mListener;

    public SearchItemView(Context context) {
        this(context, null);
    }

    public SearchItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        setSheetAttrs(0, 0, 0, 0, 0, 0,
                STROKE_COLOR, STROKE_WIDTH, 0, 0, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
        setGravity(Gravity.CENTER);
        setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_IN_SP);
    }

    public void updateView(@NonNull Movies config) {
        Log.d("app", "updating search view to " + config.getTitle());
        setText(config.getTitle());
    }
}
