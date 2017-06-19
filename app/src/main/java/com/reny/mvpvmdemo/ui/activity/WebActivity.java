package com.reny.mvpvmdemo.ui.activity;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseActivity;
import com.reny.mvpvmdemo.databinding.ActivityMainBinding;
import com.reny.mvpvmlib.base.RBasePresenter;

public class WebActivity extends MyBaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected RBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
