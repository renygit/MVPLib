package com.reny.mvpvmlib.base;

import android.app.Application;

import com.reny.mvpvmlib.register.AppRegister;

/**
 * Created by reny on 2017/6/5.
 */

public class BaseApplication extends Application {

    private static final AppRegister mAppRegister = new AppRegister();
    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mAppRegister.register(this);
    }


    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppRegister.onTerminate();
    }
}
