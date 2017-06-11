package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentABinding;
import com.reny.mvpvmdemo.presenter.FAPresenter;
import com.reny.mvpvmdemo.vm.FAViewModel;
import com.reny.mvpvmlib.base.BasePresenter;

/**
 * Created by admin on 2017/6/7.
 */

public class FragmentA extends MyBaseFragment<FragmentABinding> {

    private FAPresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        if(null == presenter){
            presenter = new FAPresenter(this, new FAViewModel());
        }
        return presenter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding.setPresenter(presenter);
        binding.setViewModel(presenter.getViewModel());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }
}
