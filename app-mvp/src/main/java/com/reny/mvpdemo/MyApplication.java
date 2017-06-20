package com.reny.mvpdemo;

import android.app.Application;
import android.content.Context;

import com.reny.mvpdemo.utils.img.ImageUtils;
import com.reny.mvpdemo.utils.img.glide.GlideLoadStrategy;
import com.reny.mvpvmlib.utils.LogUtils;
import com.zhy.changeskin.SkinManager;

/**
 * Created by reny on 2017/6/6.
 */

public class MyApplication extends Application {

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
    }

    private void init() {
        //AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        ImageUtils.getInstance().init(new GlideLoadStrategy());
        LogUtils.init(BuildConfig.DEBUG);
        SkinManager.getInstance().init(this);
        LogUtils.isPrintResponseData = BuildConfig.DEBUG;//是否打印网络返回的源数据
        LogUtils.isPrintResponseData = false;
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
