package com.reny.mvpvmdemo.core;

import android.databinding.ObservableBoolean;

import com.reny.mvpvmdemo.BR;
import com.reny.mvpvmlib.base.BaseViewModel;

/**
 * Created by reny on 2017/6/8.
 */

public class MyBaseViewModel extends BaseViewModel {

    public ObservableBoolean loading = new ObservableBoolean(false);
    public StateViewType stateType = StateViewType.LOADING;

    public void setStateType(StateViewType stateType) {
        this.stateType = stateType;
        //notifyPropertyChanged();
    }

    public enum  StateViewType{
        LOADING,EMPTY,ERROR,NONET,SHOWCONTENT
    }

}
