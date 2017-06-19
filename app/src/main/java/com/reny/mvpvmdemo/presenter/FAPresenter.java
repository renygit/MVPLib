package com.reny.mvpvmdemo.presenter;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.api.GankApiService;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.core.ServiceHelper;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmdemo.vm.FAViewModel;
import com.reny.mvpvmlib.base.IRBaseView;

import cn.bingoogolapple.androidcommon.adapter.BGABindingViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/6/6.
 */

public class FAPresenter extends MyBasePresenter<IRBaseView, FAViewModel> {

    private String category = GankApiService.category_a;
    private int count = ResUtils.getInteger(R.integer.load_count);
    private int page = 1;

    public FAPresenter(IRBaseView view, FAViewModel viewModel) {
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

    public void onClickItem(BGABindingViewHolder holder, GankData.ResultsBean model) {

    }
}
