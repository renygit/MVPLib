package com.reny.mvpvmdemo.presenter;

import android.content.Intent;

import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.core.ServiceHelper;
import com.reny.mvpvmdemo.entity.model.HotMovieData;
import com.reny.mvpvmdemo.presenter.vm.FBViewModel;
import com.reny.mvpvmdemo.ui.activity.WebActivity;
import com.reny.mvpvmlib.base.IRBaseView;

import cn.bingoogolapple.androidcommon.adapter.BGABindingViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by reny on 2017/1/4.
 */

public class FBPresenter extends MyBasePresenter<IRBaseView, FBViewModel> {

    public FBPresenter(IRBaseView view, FBViewModel viewModel) {
        super(view, viewModel);
    }

    @Override
    public void onCreate() {
        getViewModel().adapter.setItemEventHandler(this);
        loadData(true);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        addDisposable(ServiceHelper.getDoubanAS().getHotMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HotMovieData>() {
                    @Override
                    public void onNext(HotMovieData value) {
                        getViewModel().setData(value, isRefresh);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewModel().onError(e, isRefresh);
                    }

                    @Override
                    public void onComplete() {}
                })
        );
    }

    //列表Item点击 与xml绑定
    public void onClickItem(BGABindingViewHolder holder, HotMovieData.SubjectsBean model) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", model.getAlt());
        context.startActivity(intent);
    }


    public static String getDirectors(HotMovieData.SubjectsBean model){
        if(null == model)return null;
        String directors = "导演：";
        for (int i = 0; i < model.getDirectors().size(); i++) {
            directors += model.getDirectors().get(i).getName();
            if(i != model.getDirectors().size()-1)directors += "、";
        }
        return directors;
    }

    public static String getCasts(HotMovieData.SubjectsBean model){
        if(null == model)return null;
        String casts = "主演：";
        for (int i = 0; i < model.getCasts().size(); i++) {
            casts += model.getCasts().get(i).getName();
            if(i != model.getCasts().size()-1)casts += "/";
        }
        return casts;
    }

    public static String getGenres(HotMovieData.SubjectsBean model){
        if(null == model)return null;
        String genres = "类型：";
        for (int i = 0; i < model.getGenres().size(); i++) {
            genres += model.getGenres().get(i);
            if(i != model.getGenres().size()-1)genres += "/";
        }
        return genres;
    }

}