package com.reny.mvpvmdemo.core;

import android.app.Activity;

import com.reny.mvpvmlib.base.IRBaseView;
import com.reny.mvpvmlib.base.RBasePresenter;
import com.renygit.multistateview.MultiStateView;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IRBaseView, VM extends MyBaseViewModel> extends RBasePresenter<V,VM> {

    protected Activity context;

    public MyBasePresenter(V view, VM viewModel) {
        super(view, viewModel);
        context = view.getActivity();
    }

    public void retry(){
        getViewModel().stateViewType.set(MultiStateView.STATE_LOADING);
        loadData(true);
    }

    public void loadData(boolean isRefresh) {

    }

}
