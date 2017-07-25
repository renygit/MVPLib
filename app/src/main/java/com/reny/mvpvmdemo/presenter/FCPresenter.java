package com.reny.mvpvmdemo.presenter;

import android.content.Intent;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.api.GankApiService;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.core.ServiceHelper;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.presenter.vm.FCViewModel;
import com.reny.mvpvmdemo.ui.activity.WebActivity;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmlib.base.IRBaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by reny on 2017/1/4.
 */

public class FCPresenter extends MyBasePresenter<IRBaseView, FCViewModel> implements BaseViewAdapter.Presenter{

    private String category = GankApiService.category_b;
    private int count = ResUtils.getInteger(R.integer.load_count);
    int page = 1;

    public FCPresenter(IRBaseView view, FCViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {
        getViewModel().adapter.setPresenter(this);
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
    public void onClickItem(GankData.ResultsBean model) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", model.getUrl());
        context.startActivity(intent);
    }

}
