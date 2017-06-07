package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmlib.base.BasePresenter;

/**
 * Created by admin on 2017/6/7.
 */

public class FragmentA extends MyBaseFragment {

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }
}
