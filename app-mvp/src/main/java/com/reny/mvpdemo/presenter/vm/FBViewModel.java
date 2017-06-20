package com.reny.mvpdemo.presenter.vm;

import android.databinding.ObservableBoolean;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBaseViewModel;
import com.reny.mvpdemo.databinding.ItemFragmentBBinding;
import com.reny.mvpdemo.entity.model.HotMovieData;
import com.reny.mvpdemo.utils.CommonUtils;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/1/4.
 */

public class FBViewModel extends MyBaseViewModel {

    public ObservableBoolean loadMoreEnable = new ObservableBoolean(false);
    public BGABindingRecyclerViewAdapter<HotMovieData.SubjectsBean, ItemFragmentBBinding> innerAdapter = new BGABindingRecyclerViewAdapter<>(R.layout.item_fragment_b);
    public LRecyclerViewAdapter adapter = new LRecyclerViewAdapter(innerAdapter);

    public void setData(HotMovieData data, boolean isRefresh) {
        setDataState(CommonUtils.isEmpty(data.getSubjects()));
        if(isRefresh){
            innerAdapter.clear();
            innerAdapter.addNewData(data.getSubjects());
        }else {
            innerAdapter.addMoreData(data.getSubjects());
        }
    }

}
