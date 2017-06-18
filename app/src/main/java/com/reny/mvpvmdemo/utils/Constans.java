package com.reny.mvpvmdemo.utils;

import android.Manifest;

/**
 * Created by reny on 2017/6/17.
 */

public class Constans {

    /***
     * 检查定位需要的权限,在Presenter中直接如下使用
     * checkPermissions(onNext, LocationPermissions);
     */
    public static final String[] LocationPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};

    /**
     * 检查选择图片，包括使用摄像头等权限
     */
    public static final String[] ChooseImgPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 检查外部存储的权限
     */
    public static final String[] StoragePermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 检查发送短信权限
     */
    public static final String[] SendSmsPermissions = {
            Manifest.permission.SEND_SMS
    };

    /**
     * 检查打电话权限
     */
    public static final String[] CallPhonePermissions = {
            Manifest.permission.CALL_PHONE
    };

    /**
     * 检查获取手机状态的权限
     */
    public static final String[] ReadPhoneStatePermissions = {
            Manifest.permission.READ_PHONE_STATE
    };


}
