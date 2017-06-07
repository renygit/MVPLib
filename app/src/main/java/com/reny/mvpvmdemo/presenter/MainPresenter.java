package com.reny.mvpvmdemo.presenter;

import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmlib.base.BaseViewModel;
import com.reny.mvpvmlib.base.IBaseView;

/**
 * Created by admin on 2017/6/6.
 */

public class MainPresenter extends MyBasePresenter<IBaseView, BaseViewModel> {

    public MainPresenter(IBaseView view, BaseViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {

        /*ADRequest request = new ADRequest();
        request.setCount(1);

        RequestBody requestBody = getBody(new DefaultRequsetBuilder().Builder("AndroidPushQueryService/GetHomePageTopicInfo").set(request).builder());

        addDisposable(ServiceHelper.getAS().getAD(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ADResponse>() {
                    @Override
                    public void onNext(ADResponse value) {
                        LogUtils.json(GsonSingleton.gson.toJson(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                })
        );*/

    }
}
