package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentDBinding;
import com.reny.mvpvmlib.base.RBasePresenter;
import com.zhy.changeskin.SkinManager;

/**
 * Created by reny on 2017/1/9.
 */

public class FragmentD extends MyBaseFragment<FragmentDBinding> {

    @Override
    protected RBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_d;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        //未保存switch状态
        binding.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SkinManager.getInstance().changeSkin(isChecked ? "night":"");
            }
        });
    }

}
