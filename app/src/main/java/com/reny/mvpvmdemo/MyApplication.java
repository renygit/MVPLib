package com.reny.mvpvmdemo;

import android.content.Context;

import com.reny.mvpvmdemo.utils.img.GlideLoadStrategy;
import com.reny.mvpvmdemo.utils.img.ImageUtils;
import com.reny.mvpvmlib.utils.LogUtils;
import com.reny.mvpvmlib.base.BaseApplication;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.changeskin.SkinManager;

/**
 * Created by reny on 2017/6/6.
 */

public class MyApplication extends BaseApplication {

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
    }

    private void init() {
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        ImageUtils.getInstance().init(new GlideLoadStrategy());
        LogUtils.init(BuildConfig.DEBUG);
        SkinManager.getInstance().init(this);
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}