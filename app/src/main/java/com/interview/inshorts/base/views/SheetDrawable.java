package com.interview.inshorts.base.views;

import android.graphics.drawable.GradientDrawable;

public class SheetDrawable extends GradientDrawable {

    public void setRoundedCorners(float left, float top, float right, float bottom) {
        setCornerRadii(new float[]{left, left, top, top, right, right, bottom, bottom});
    }

    public void setBgColor(int color) {
        setColor(color);
        clearColorFilter();
    }

    public void setBgGradient(int[] colors, Orientation orientation) {
        setOrientation(orientation);
        setColors(colors);
        clearColorFilter();
    }
}

