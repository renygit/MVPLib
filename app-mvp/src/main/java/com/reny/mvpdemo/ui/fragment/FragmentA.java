package com.reny.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBaseFragment;
import com.reny.mvpdemo.databinding.FragmentABinding;
import com.reny.mvpdemo.entity.model.GankData;
import com.reny.mvpdemo.presenter.FAPresenter;
import com.reny.mvpdemo.ui.adapter.FAAdapter;
import com.reny.mvpdemo.ui.iview.FAView;
import com.reny.mvpdemo.utils.BindingEvent;
import com.reny.mvpdemo.utils.CommonUtils;
import com.reny.mvpdemo.utils.Network;
import com.reny.mvpdemo.utils.ResUtils;
import com.reny.mvpdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.RBasePresenter;

/**
 * Created by admin on 2017/6/7.
 */

public class FragmentA extends MyBaseFragment<FragmentABinding> implements FAView {

    private FAPresenter presenter;
    private boolean isFirst = true;
    private FAAdapter innerAdapter;
    private LRecyclerViewAdapter adapter;

    @Override
    protected RBasePresenter getPresenter() {
        if (null == presenter) {
            presenter = new FAPresenter(this, null);
        }
        return presenter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding.rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        if(null == innerAdapter){
            innerAdapter = new FAAdapter(binding.rv);
            adapter = new LRecyclerViewAdapter(innerAdapter);
            binding.rv.setAdapter(adapter);
        }

        binding.msv.showLoading();
        binding.msv.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData(true);
            }
        });

        BindingEvent.setRefreshPresenter(binding.pfl, presenter);
        BindingEvent.setLoadMore(binding.rv, presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }

    @Override
    public void setData(GankData data, boolean isRefresh) {
        refreshComplete();
        if(CommonUtils.isEmpty(data.getResults())){//目前通过这个判断是否有更多数据 具体情况可以根据项目实际情况判断
            if(isFirst){
                binding.pfl.setVisibility(View.GONE);
                binding.msv.showEmpty();
            }else {
                if(isRefresh) {
                    ToastUtils.showShort("未获取到新数据");
                }else {
                    binding.rv.setNoMore(true);//没有更多
                }
            }
        }else {
            isFirst = false;
            binding.msv.showContent();
            binding.pfl.setVisibility(View.VISIBLE);
            if(isRefresh){
                innerAdapter.clear();
                innerAdapter.addNewData(data.getResults());
            }else {
                innerAdapter.addMoreData(data.getResults());
            }
        }
    }

    @Override
    public void onError(Throwable e, boolean isRefresh) {
        refreshComplete();
        if(isFirst){
            binding.pfl.setVisibility(View.GONE);
            if(Network.isConnected()) {
                binding.msv.showError();
            }else {
                binding.msv.showNoNetwork();
            }
        }else{
            if(isRefresh){
                ToastUtils.showShort("刷新失败，请重试");
            }else {
                binding.rv.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                    @Override
                    public void reload() {
                        presenter.loadData(false);
                    }
                });
            }
        }
    }

    private void refreshComplete(){
        binding.pfl.refreshComplete();
        binding.rv.refreshComplete(presenter.count);
    }
}
