package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentBBinding;
import com.reny.mvpvmdemo.presenter.FBPresenter;
import com.reny.mvpvmdemo.presenter.vm.FBViewModel;
import com.reny.mvpvmlib.base.RBasePresenter;

/**
 * Created by reny on 2017/1/9.
 */

public class FragmentB extends MyBaseFragment<FragmentBBinding> {

    private FBPresenter presenter;

    @Override
    protected RBasePresenter getPresenter() {
        if(null == presenter){
            presenter = new FBPresenter(this, new FBViewModel());
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
        return R.layout.fragment_b;
    }
}
