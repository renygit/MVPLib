package com.reny.mvpvmlib.base;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.reny.mvpvmlib.register.ActivityRegister;

/**
 * Created by admin on 2017/6/5.
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity implements IBaseView{

    protected DB binding;
    protected ActivityRegister mActivityRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        preOnCreate(savedInstanceState);//特殊需求 一般不用重写
        super.onCreate(savedInstanceState);
        //多个Presenter或不想继承本BaseActivity，重写以下两句关键代码即可
        mActivityRegister = new ActivityRegister().initBinding(this, getLayoutId()).register(getPresenter());
        binding = (DB)mActivityRegister.getBinding();

        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityRegister.unRegister();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    protected abstract int getLayoutId();

    protected abstract BasePresenter getPresenter();

    protected void preOnCreate(Bundle savedInstanceState){}

    protected abstract void init(Bundle savedInstanceState);

}
