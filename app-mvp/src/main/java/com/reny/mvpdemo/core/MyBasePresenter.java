package com.reny.mvpdemo.core;

import android.app.Activity;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmlib.base.IRBaseView;
import com.reny.mvpvmlib.base.RBasePresenter;
import com.reny.mvpvmlib.base.RBaseViewModel;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IRBaseView, VM extends RBaseViewModel> extends RBasePresenter<V,VM> {

    protected Activity context;

    public MyBasePresenter(V view, VM viewModel) {
        super(view, viewModel);
        context = view.getActivity();
    }

    public void loadData(boolean isRefresh) {

    }

}
