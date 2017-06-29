package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentBBinding;
import com.reny.mvpvmdemo.entity.event.RvScrollEvent;
import com.reny.mvpvmdemo.presenter.FBPresenter;
import com.reny.mvpvmdemo.presenter.vm.FBViewModel;
import com.reny.mvpvmlib.base.RBasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by reny on 2017/1/9.
 */

public class FragmentB extends MyBaseFragment<FragmentBBinding> {

    private FBPresenter presenter;

    private LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RvScrollEvent event) {
        if (event.getTabIndex() == 1) {
            layoutManager.scrollToPositionWithOffset(event.getPos(), 0);
        }
    }

    @Override
    protected boolean isEnableEventBus() {
        return true;
    }

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
        binding.setLayoutManager(layoutManager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_b;
    }
}
