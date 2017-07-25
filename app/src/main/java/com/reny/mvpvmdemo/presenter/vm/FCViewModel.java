package com.reny.mvpvmdemo.presenter.vm;

import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.reny.mvpvmdemo.MyApplication;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.utils.CommonUtils;

/**
 * Created by reny on 2017/1/4.
 */

public class FCViewModel extends MyBaseViewModel {

    public SingleTypeAdapter<GankData.ResultsBean> adapter = new SingleTypeAdapter<>(MyApplication.getContext(), R.layout.item_fragment_c);

    public void setData(GankData data, boolean isRefresh) {
        boolean isEmpty = CommonUtils.isEmpty(data.getResults());
        if(!isEmpty){
            if(isRefresh){
                adapter.set(data.getResults());
            }else {
                adapter.addAll(data.getResults());
            }
        }
        setDataState(isEmpty);
    }

}
