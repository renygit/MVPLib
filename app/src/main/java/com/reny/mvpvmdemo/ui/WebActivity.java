package com.reny.mvpvmdemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseActivity;
import com.reny.mvpvmdemo.databinding.ActivityMainBinding;
import com.reny.mvpvmdemo.presenter.MainPresenter;
import com.reny.mvpvmdemo.ui.fragment.FragmentA;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.BasePresenter;
import com.reny.mvpvmlib.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class WebActivity extends MyBaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
