package com.reny.mvpdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.reny.mvpdemo.R;
import com.reny.mvpdemo.entity.model.GankData;
import com.reny.mvpdemo.utils.img.ImageUtils;
import com.reny.mvpdemo.widget.RatioImageView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by reny on 2017/6/20.
 */

public class FAAdapter extends BGARecyclerViewAdapter<GankData.ResultsBean> {

    public FAAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_fragment_a);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, GankData.ResultsBean model) {
        RatioImageView iv_img = helper.getView(R.id.iv_img);
        ImageUtils.getInstance().disPlay(model.getUrl(), iv_img);
        helper.setText(R.id.tv_txt, model.getCreatedAt().substring(0,10));
    }
}
