package com.reny.mvpvmlib.base;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by admin on 2017/6/5.
 */

public abstract class BasePresenter<V extends IBaseView, VM extends BaseViewModel>{

    private V view;
    private VM viewModel;
    public V getView() {
        return view;
    }
    public VM getViewModel() {
        return viewModel;
    }

    private CompositeDisposable mCompositeDisposable;
    private RxPermissions rxPermissions;

    public BasePresenter(V view, VM viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void addDisposable(Disposable disposable) {
        if(null == mCompositeDisposable)mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    public void checkPermissions(DisposableObserver<Boolean> onNext, String... permissions) {
        if(null == rxPermissions)rxPermissions = new RxPermissions(this.view.getActivity());
        addDisposable(rxPermissions.request(permissions).subscribeWith(onNext));
    }

    public abstract void onCreate();

    public void onDestroy(){
        this.view = null;
        this.viewModel = null;
        if(null != mCompositeDisposable) {
            this.mCompositeDisposable.clear();
            this.mCompositeDisposable = null;
        }
    }

}
