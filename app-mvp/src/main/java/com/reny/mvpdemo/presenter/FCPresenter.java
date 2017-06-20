package com.reny.mvpdemo.presenter;

import android.content.Intent;

import com.reny.mvpdemo.R;
import com.reny.mvpdemo.api.GankApiService;
import com.reny.mvpdemo.core.MyBasePresenter;
import com.reny.mvpdemo.core.ServiceHelper;
import com.reny.mvpdemo.entity.model.GankData;
import com.reny.mvpdemo.presenter.vm.FCViewModel;
import com.reny.mvpdemo.ui.activity.WebActivity;
import com.reny.mvpdemo.utils.ResUtils;
import com.reny.mvpvmlib.base.IRBaseView;

import cn.bingoogolapple.androidcommon.adapter.BGABindingViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by reny on 2017/1/4.
 */

public class FCPresenter extends MyBasePresenter<IRBaseView, FCViewModel> {

    private String category = GankApiService.category_b;
    private int count = ResUtils.getInteger(R.integer.load_count);
    int page = 1;

    public FCPresenter(IRBaseView view, FCViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {
        getViewModel().innerAdapter.setItemEventHandler(this);
        loadData(true);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        addDisposable(ServiceHelper.getGankAS().getGankIoData(category, count, isRefresh ? 1 : page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GankData>() {
                    @Override
                    public void onNext(GankData value) {
                        page = isRefresh ? 2 : ++page;
                        getViewModel().setData(value, isRefresh);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewModel().onError(e, isRefresh);
                    }

                    @Override
                    public void onComplete() {}
                })
        );
    }

    //列表Item点击 与xml绑定
    public void onClickItem(BGABindingViewHolder holder, GankData.ResultsBean model) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", model.getUrl());
        context.startActivity(intent);
    }

}
