package com.interview.inshorts.base.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.interview.inshorts.R;
import com.interview.inshorts.base.views.SheetDrawable;

import androidx.annotation.Nullable;

public class SheetViewHelper {
    public void addSheetByAttributes(Context context, View view, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SheetStyleable,
                defStyleAttr, 0);
        boolean hasSheet = a.getBoolean(R.styleable.SheetStyleable_olSheet, false);
        if (hasSheet) {
            float cornerRadius = a.getDimension(R.styleable.SheetStyleable_olSheetCornerRadius, 0f);
            int bgColor = a.getColor(R.styleable.SheetStyleable_olSheetColor, 0);
            int startColor = a.getColor(R.styleable.SheetStyleable_olSheetStartColor, 0);
            int midColor = a.getColor(R.styleable.SheetStyleable_olSheetMidColor, 0);
            int endColor = a.getColor(R.styleable.SheetStyleable_olSheetEndColor, 0);
            int gradientAngle = a.getInt(R.styleable.SheetStyleable_olSheetGradientAngle, 0);
            int strokeColor = a.getColor(R.styleable.SheetStyleable_olSheetStrokeColor, 0);
            int gradientType = a.getColor(R.styleable.SheetStyleable_olSheetGradientType, 0);
            float strokeWidth = a.getDimension(R.styleable.SheetStyleable_olSheetStrokeWidth, 0f);
            float strokeDashWidth = a.getDimension(R.styleable.SheetStyleable_olSheetStrokeDashWidth, 0f);
            float strokeDashGap = a.getDimension(R.styleable.SheetStyleable_olSheetStrokeDashGap, 0f);
            SheetDrawable sheet = fetchSheetDrawable(cornerRadius, bgColor, startColor, midColor, endColor, gradientAngle, strokeColor, (int)strokeWidth, strokeDashWidth, strokeDashGap, gradientType);
            view.setBackground(sheet);
        }
    }

    public void setSheetAttrs(View view,
                              float cornerRadius,
                              int bgColor,
                              int startColor,
                              int midColor,
                              int endColor,
                              int gradientAngle,
                              int strokeColor,
                              int strokeWidth,
                              float strokeDashWidth,
                              float strokeDashGap,
                              int gradientType) {
        view.setBackground(fetchSheetDrawable(cornerRadius, bgColor, startColor, midColor,
                endColor, gradientAngle, strokeColor, strokeWidth, strokeDashWidth,
                strokeDashGap, gradientType));
    }

    private SheetDrawable fetchSheetDrawable(
            float cornerRadius,
            int bgColor,
            int startColor,
            int midColor,
            int endColor,
            int gradientAngle,
            int strokeColor,
            int strokeWidth,
            float strokeDashWidth,
            float strokeDashGap,
            int gradientType
    ) {
        SheetDrawable sheet = new SheetDrawable();
        sheet.setRoundedCorners(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
        if (startColor == 0) {
            if (bgColor != 0) {
                sheet.setBgColor(bgColor);
            }
        } else {
            GradientDrawable.Orientation orientation = getOrientation(gradientAngle);
            int[] colors = new int[]{startColor, endColor};
            sheet.setBgGradient(colors, orientation);
            setGradientType(sheet, gradientType);
        }
        sheet.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeDashGap);
        return sheet;
    }

    private void setGradientType(SheetDrawable sheet, int gradientType) {
        if (gradientType == 1) {
            sheet.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        } else {
            sheet.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        }
    }

    private GradientDrawable.Orientation getOrientation(int gradientAngle) {
        switch (gradientAngle) {
            case 1:
                return GradientDrawable.Orientation.RIGHT_LEFT;
            case 2:
                return GradientDrawable.Orientation.TOP_BOTTOM;
            case 3:
                return GradientDrawable.Orientation.BOTTOM_TOP;
            case 4:
                return GradientDrawable.Orientation.TL_BR;
            case 5:
                return GradientDrawable.Orientation.BR_TL;
            case 6:
                return GradientDrawable.Orientation.TR_BL;
            case 7:
                return GradientDrawable.Orientation.BL_TR;
            default:
                return GradientDrawable.Orientation.LEFT_RIGHT;
        }
    }
}
