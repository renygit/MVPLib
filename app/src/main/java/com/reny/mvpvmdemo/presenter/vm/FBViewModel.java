package com.reny.mvpvmdemo.presenter.vm;

import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.reny.mvpvmdemo.MyApplication;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseViewModel;
import com.reny.mvpvmdemo.entity.model.HotMovieData;
import com.reny.mvpvmdemo.utils.CommonUtils;
import com.reny.mvpvmlib.utils.LogUtils;

/**
 * Created by reny on 2017/1/4.
 */

public class FBViewModel extends MyBaseViewModel {

    public SingleTypeAdapter<HotMovieData.SubjectsBean> adapter = new SingleTypeAdapter<>(MyApplication.getContext(), R.layout.item_fragment_b);

    public void setData(HotMovieData data, boolean isRefresh) {
        boolean isEmpty = CommonUtils.isEmpty(data.getSubjects());
        if(!isEmpty){
            if(isRefresh){
                adapter.set(data.getSubjects());
            }else {
                adapter.addAll(data.getSubjects());
            }
        }
        setDataState(isEmpty);
    }

}
