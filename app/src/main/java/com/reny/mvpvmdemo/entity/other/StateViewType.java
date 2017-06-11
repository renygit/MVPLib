package com.reny.mvpvmdemo.entity.other;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.reny.mvpvmdemo.BR;

/**
 * Created by reny on 2017/6/11.
 */

public class StateViewType extends BaseObservable{

    private StateType state = StateType.LOADING;

    @Bindable
    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    public enum StateType{
        LOADING,EMPTY,ERROR,NONET,SHOWCONTENT;
    }
}
