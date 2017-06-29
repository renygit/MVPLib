package com.reny.mvpvmdemo.presenter;

import android.content.Intent;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.api.GankApiService;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.core.ServiceHelper;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.entity.other.ImgsInfo;
import com.reny.mvpvmdemo.presenter.vm.FAViewModel;
import com.reny.mvpvmdemo.ui.activity.ImagesActivity;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmlib.base.IRBaseView;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> imgUrls;

    public FAPresenter(IRBaseView view, FAViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {
        getViewModel().adapter.setItemEventHandler(this);
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
        Intent intent = new Intent(context, ImagesActivity.class);
        if(null == imgUrls) {
            imgUrls = new ArrayList<>(count);
        }
        imgUrls.clear();
        for (GankData.ResultsBean bean : getViewModel().adapter.getData()) {
            imgUrls.add(bean.getUrl());
        }

        int curPos = holder.getAdapterPositionWrapper() - 1;

        intent.putExtra(ImgsInfo.KEY, new ImgsInfo(imgUrls, curPos < 0 ? 0 : curPos));
        context.startActivity(intent);
    }
}
