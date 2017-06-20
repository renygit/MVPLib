package com.reny.mvpvmdemo.vm;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.databinding.ItemFragmentCBinding;
import com.reny.mvpvmdemo.entity.model.GankData;
import com.reny.mvpvmdemo.utils.CommonUtils;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/1/4.
 */

public class FCViewModel extends MyBaseViewModel {

    public StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    public BGABindingRecyclerViewAdapter<GankData.ResultsBean, ItemFragmentCBinding> innerAdapter = new BGABindingRecyclerViewAdapter<>(R.layout.item_fragment_c);
    public LRecyclerViewAdapter adapter = new LRecyclerViewAdapter(innerAdapter);

    public void setData(GankData data, boolean isRefresh) {
        setDataState(CommonUtils.isEmpty(data.getResults()));
        if(isRefresh){
            innerAdapter.clear();
            innerAdapter.addNewData(data.getResults());
        }else {
            innerAdapter.addMoreData(data.getResults());
        }
    }

}