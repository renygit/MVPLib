package com.reny.mvpvmdemo.presenter.vm;

import com.reny.mvpvmdemo.MyApplication;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.core.SingleAdapter;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.utils.CommonUtils;

/**
 * Created by reny on 2017/6/8.
 */

public class FAViewModel extends MyBaseViewModel {

    public SingleAdapter<GankData.ResultsBean> adapter = new SingleAdapter<>(MyApplication.getContext(), R.layout.item_fragment_a);


    public void setData(GankData data, boolean isRefresh){
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
