package com.reny.mvpvmdemo.presenter.vm;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.databinding.ItemFragmentABinding;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.utils.CommonUtils;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/6/8.
 */

public class FAViewModel extends MyBaseViewModel {

    public BGABindingRecyclerViewAdapter<GankData.ResultsBean, ItemFragmentABinding> adapter = new BGABindingRecyclerViewAdapter<>(R.layout.item_fragment_a);


    public void setData(GankData data, boolean isRefresh){
        setDataState(CommonUtils.isEmpty(data.getResults()));
        if(isRefresh){
            adapter.clear();
            adapter.addNewData(data.getResults());
        }else {
            adapter.addMoreData(data.getResults());
        }
    }

}
