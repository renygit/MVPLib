package com.reny.mvpvmdemo.presenter.vm;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.databinding.ItemFragmentBBinding;
import com.reny.mvpvmdemo.entity.model.HotMovieData;
import com.reny.mvpvmdemo.utils.CommonUtils;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/1/4.
 */

public class FBViewModel extends MyBaseViewModel {

    public BGABindingRecyclerViewAdapter<HotMovieData.SubjectsBean, ItemFragmentBBinding> adapter = new BGABindingRecyclerViewAdapter<>(R.layout.item_fragment_b);


    public void setData(HotMovieData data, boolean isRefresh) {
        setDataState(CommonUtils.isEmpty(data.getSubjects()));
        if(isRefresh){
            adapter.clear();
            adapter.addNewData(data.getSubjects());
        }else {
            adapter.addMoreData(data.getSubjects());
        }
    }

}
