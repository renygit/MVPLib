package com.reny.mvpvmdemo;

import android.content.Context;

import com.reny.mvpvmlib.utils.LogUtils;
import com.reny.mvpvmlib.base.BaseApplication;
import com.zhy.changeskin.SkinManager;

/**
 * Created by admin on 2017/6/6.
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
        LogUtils.init(BuildConfig.DEBUG);
        SkinManager.getInstance().init(this);
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
