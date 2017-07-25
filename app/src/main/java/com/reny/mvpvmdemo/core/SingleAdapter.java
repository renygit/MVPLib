package com.reny.mvpvmdemo.core;

import android.content.Context;

import com.github.markzhai.recyclerview.BindingViewHolder;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.reny.mvpvmdemo.BR;

import java.util.List;

/**
 * Created by admin on 2017/7/24.
 */

public class SingleAdapter<T> extends SingleTypeAdapter<T> {

    public SingleAdapter(Context context) {
        super(context);
    }

    public SingleAdapter(Context context, int layoutRes) {
        super(context, layoutRes);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.holder, holder);
        super.onBindViewHolder(holder, position);
    }

    public List<T> getDatas(){
        return mCollection;
    }

}
