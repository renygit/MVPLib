package com.reny.mvpvmdemo.vm;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.databinding.ItemFragmentABinding;
import com.reny.mvpvmdemo.entity.model.GankData;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/6/8.
 */

public class FAViewModel extends MyBaseViewModel {

    public StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    public BGABindingRecyclerViewAdapter<GankData.ResultsBean, ItemFragmentABinding> adapter = new BGABindingRecyclerViewAdapter<>(R.layout.item_fragment_a);

    public void setData(boolean isRefresh, GankData data){
        setDataState(data.getResults());
        //adapter.addFooterView();
        //adapter.getHeaderAndFooterAdapter()
        if(isRefresh){
            adapter.clear();
            adapter.addNewData(data.getResults());
        }else {
            adapter.addMoreData(data.getResults());
        }
    }

}
