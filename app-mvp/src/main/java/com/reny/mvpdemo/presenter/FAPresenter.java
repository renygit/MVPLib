package com.reny.mvpdemo.presenter;

import com.reny.mvpdemo.R;
import com.reny.mvpdemo.api.GankApiService;
import com.reny.mvpdemo.core.MyBasePresenter;
import com.reny.mvpdemo.core.ServiceHelper;
import com.reny.mvpdemo.entity.model.GankData;
import com.reny.mvpdemo.ui.iview.FAView;
import com.reny.mvpdemo.utils.ResUtils;
import com.reny.mvpvmlib.base.RBaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/6/6.
 */

public class FAPresenter extends MyBasePresenter<FAView, RBaseViewModel> {

    private String category = GankApiService.category_a;
    public int count = ResUtils.getInteger(R.integer.load_count);
    private int page = 1;

    private List<String> imgUrls;

    public FAPresenter(FAView view, RBaseViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {
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
                        getView().setData(value, isRefresh);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onError(e, isRefresh);
                    }

                    @Override
                    public void onComplete() {}
                })
        );
    }

    /*public void onClickItem(BGABindingViewHolder holder, GankData.ResultsBean model) {
        Intent intent = new Intent(context, ImagesActivity.class);
        if(null == imgUrls) {
            imgUrls = new ArrayList<>(count);
        }
        imgUrls.clear();
        for (GankData.ResultsBean bean : getViewModel().innerAdapter.getData()) {
            imgUrls.add(bean.getUrl());
        }

        int curPos = holder.getAdapterPositionWrapper() - 1;

        intent.putExtra(ImgsInfo.KEY, new ImgsInfo(imgUrls, curPos < 0 ? 0 : curPos));
        context.startActivity(intent);
    }*/
}
