package com.reny.mvpvmdemo.core;

import android.app.Activity;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmlib.base.IRBaseView;
import com.reny.mvpvmlib.base.RBasePresenter;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IRBaseView, VM extends MyBaseViewModel> extends RBasePresenter<V,VM> {

    protected Activity context;

    public MyBasePresenter(V view, VM viewModel) {
        super(view, viewModel);
        context = view.getActivity();
    }

    public void requestData(boolean isRefresh){
        if(getViewModel().firstLoadData){
            getViewModel().stateViewType.set(MultipleStatusView.STATUS_LOADING);
        }
        loadData(isRefresh);
    }

    public void loadData(boolean isRefresh) {

    }

}
