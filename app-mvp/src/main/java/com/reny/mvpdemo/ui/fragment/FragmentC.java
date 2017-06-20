package com.reny.mvpdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBaseFragment;
import com.reny.mvpdemo.databinding.FragmentCBinding;
import com.reny.mvpdemo.presenter.FCPresenter;
import com.reny.mvpdemo.presenter.vm.FCViewModel;
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
