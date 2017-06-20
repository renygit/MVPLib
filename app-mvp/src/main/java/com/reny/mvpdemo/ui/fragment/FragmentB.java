package com.reny.mvpdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBaseFragment;
import com.reny.mvpdemo.databinding.FragmentBBinding;
import com.reny.mvpdemo.presenter.FBPresenter;
import com.reny.mvpdemo.presenter.vm.FBViewModel;
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
