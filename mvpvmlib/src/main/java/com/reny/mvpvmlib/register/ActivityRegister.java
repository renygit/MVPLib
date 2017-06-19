package com.reny.mvpvmlib.register;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.reny.mvpvmlib.base.RBasePresenter;

/**
 * Created by reny on 2017/6/5.
 */

public class ActivityRegister {

    private ViewDataBinding binding;
    private RBasePresenter[] presenters;

    public ActivityRegister initBinding(Activity activity, @LayoutRes int layoutId){
        if(null == binding){
            binding = DataBindingUtil.setContentView(activity, layoutId);
        }
        return this;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    //register时就会启动presenter的onCreate
    public ActivityRegister register(RBasePresenter... presenters){
        this.presenters = presenters;
        if(null != this.presenters) {
            for (RBasePresenter presenter : this.presenters) {
                if (null != presenter) presenter.onCreate();
            }
        }
        return this;
    }

    public void unRegister(){
        if(null != this.presenters) {
            for (RBasePresenter presenter : this.presenters) {
                if (null != presenter) presenter.onDestroy();
            }
        }
        binding = null;
    }

}
