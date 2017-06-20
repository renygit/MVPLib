package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentCBinding;
import com.reny.mvpvmdemo.presenter.FCPresenter;
import com.reny.mvpvmdemo.presenter.vm.FCViewModel;
import com.reny.mvpvmlib.base.RBasePresenter;

/**
 * Created by reny on 2017/1/9.
 */

public class FragmentC extends MyBaseFragment<FragmentCBinding> {

    private FCPresenter presenter;

    @Override
    protected RBasePresenter getPresenter() {
        if(null == presenter){
            presenter = new FCPresenter(this, new FCViewModel());
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
        return R.layout.fragment_c;
    }
}
