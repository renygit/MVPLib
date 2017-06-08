package com.reny.mvpvmdemo.widget.autolayout;

import android.content.Context;
import android.util.AttributeSet;

import com.classic.common.MultipleStatusView;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by admin on 2017/6/8.
 */

public class AutoMultiStateView extends MultipleStatusView {

    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoMultiStateView(Context context) {
        super(context);
    }

    public AutoMultiStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public AutoRelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoRelativeLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }

}
