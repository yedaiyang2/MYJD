package com.bawei.Zhangjinfeng.view.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.Zhangjinfeng.R;

/**
 * Created by sky on 2018/05/03.
 */

public class SearchActivity extends ViewGroup {
    public SearchActivity(Context context) {
        this(context, null);
    }

    public SearchActivity(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchActivity);
        a.getColor(R.styleable.SearchActivity_textColor, 0XFFFFFFFF);
        float textSize = a.getDimension(R.styleable.SearchActivity_textSize, 36);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量view
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int tw = 0;
        int th = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (tw + child.getWidth() < width) {
            } else {
                tw = 0;
                th += child.getMeasuredHeight();
            }
            child.layout(tw, th, tw + child.getMeasuredWidth(), th + child.getMeasuredHeight());
            tw += child.getMeasuredWidth();
        }
    }
    public void btn_re(View view){
        //    Toast.makeText(getContext(),getChildAt(1)+"1234",Toast.LENGTH_SHORT).show();
    }
}
