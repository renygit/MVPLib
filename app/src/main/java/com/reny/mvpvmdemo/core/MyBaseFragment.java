package com.reny.mvpvmdemo.core;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.reny.mvpvmlib.base.RBaseFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by admin on 2017/6/7.
 */

public abstract class MyBaseFragment<DB extends ViewDataBinding> extends RBaseFragment<DB> {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if(isEnableEventBus() && !EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isEnableEventBus() && EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    //是否启用EventBus
    protected boolean isEnableEventBus(){
        return false;
    }
}
