package com.ibt.niramaya.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by rahul on 03-02-2018.
 */

public class CenteredToolbar extends Toolbar {

    private static final double WIDTH_PERCENTAGE = 0.8;

    private TextView titleView;

    public CenteredToolbar(Context context) {
        this(context, null);
    }

    public CenteredToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.toolbarStyle);
    }

    public CenteredToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = this.getChildAt(i);
            if (view instanceof RelativeLayout) {
                int width = getMeasuredWidth();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = (int) (width * WIDTH_PERCENTAGE);
                view.setLayoutParams(layoutParams);
                break;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = this.getChildAt(i);
            if (view instanceof RelativeLayout) {
                forceTitleCenter(view);
                break;
            }
        }
    }

    /**
     * Centering the layout.
     *
     * @param view The view to be centered
     */
    private void forceTitleCenter(View view) {
        int toolbarWidth = getMeasuredWidth();
        int relativeLayoutWidth = view.getMeasuredWidth();
        int newLeft = (int) (toolbarWidth - relativeLayoutWidth) / 2;
        int top = view.getTop();
        int newRight = newLeft + relativeLayoutWidth;
        int bottom = view.getBottom();
        view.layout(newLeft, top, newRight, bottom);
    }
}