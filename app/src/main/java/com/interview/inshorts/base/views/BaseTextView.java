package com.interview.inshorts.base.views;

import android.content.Context;
import android.util.AttributeSet;

import com.interview.inshorts.base.utils.SheetViewHelper;

import androidx.appcompat.widget.AppCompatTextView;

public class BaseTextView extends AppCompatTextView {
    private SheetViewHelper mSheetHelper = new SheetViewHelper();

    public BaseTextView(Context context) {
        this(context, null);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSheetHelper.addSheetByAttributes(context, this, attrs, defStyleAttr);
    }

    public void setSheetAttrs(int cornerRadius, int bgColor, int startColor, int midColor,
                              int endColor, int angle, int strokeColor, float strokeWidth,
                              float strokeDashWidth, float strokeDashGap, int gradientType) {
        mSheetHelper.setSheetAttrs(
                this, cornerRadius, bgColor, startColor, midColor, endColor, angle,
                strokeColor, Math.round(strokeWidth), strokeDashWidth, strokeDashGap, gradientType);
    }


}