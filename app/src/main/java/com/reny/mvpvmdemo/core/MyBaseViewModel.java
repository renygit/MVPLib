package com.reny.mvpvmdemo.core;

import android.databinding.ObservableBoolean;

import com.reny.mvpvmdemo.entity.other.StateViewType;
import com.reny.mvpvmlib.base.BaseViewModel;

/**
 * Created by reny on 2017/6/8.
 */

public class MyBaseViewModel extends BaseViewModel {

    public ObservableBoolean loading = new ObservableBoolean(false);
    public StateViewType stateType = new StateViewType();

    public void setStateType(StateViewType.StateType stateType) {
        this.stateType.setState(stateType);
    }

}
