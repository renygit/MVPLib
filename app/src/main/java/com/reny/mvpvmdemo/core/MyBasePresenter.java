package com.reny.mvpvmdemo.core;

import android.Manifest;

import com.reny.mvpvmlib.base.BasePresenter;
import com.reny.mvpvmlib.base.BaseViewModel;
import com.reny.mvpvmlib.base.IBaseView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IBaseView, VM extends BaseViewModel> extends BasePresenter<V,VM> {

    public MyBasePresenter(V view, VM viewModel) {
        super(view, viewModel);
    }

    public void loadData(boolean isRefresh) {
    }

    /**
     * 检查定位需要的权限
     */
    public void checkLocationPermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * 检查选择图片，包括使用摄像头等权限
     */
    public void checkChooseImgPermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 检查外部存储的权限
     */
    public void checkStoragePermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 检查发送短信权限
     */
    public void checkSendSmsPermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.SEND_SMS);
    }

    /**
     * 检查打电话权限
     */
    public void checkCallPhonePermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.CALL_PHONE);
    }

    /**
     * 检查获取手机状态的权限
     */
    public void checkReadPhoneStatePermissions(DisposableObserver<Boolean> onNext){
        checkPermissions(onNext, Manifest.permission.READ_PHONE_STATE);
    }

}
