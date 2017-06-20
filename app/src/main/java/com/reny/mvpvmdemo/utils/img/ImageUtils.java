package com.reny.mvpvmdemo.utils.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.reny.mvpvmdemo.utils.img.glide.DownCallBack;

/**
 * Created by reny on 2017/6/8.
 * 图片加载
 */

public class ImageUtils implements ImgLoadStrategy {

    private ImgLoadStrategy imageLoader;

    private ImageUtils(){}

    private static class SingletonHolder {
        private static final ImageUtils INSTANCE = new ImageUtils();
    }

    public static ImageUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void init(ImgLoadStrategy imageLoader){
        this.imageLoader = imageLoader;
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view) {
        if(null != imageLoader)imageLoader.disPlay(model, view);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @NonNull Drawable error) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder, error);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @DrawableRes int error) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder, error);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @DrawableRes int error) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder, error);
    }

    @Override
    public void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @NonNull Drawable error) {
        if(null != imageLoader)imageLoader.disPlay(model, view, placeholder, error);
    }

    @Override
    public void downLoadPic(Context context, String url, String dir, String picName, DownCallBack callBack) {
        if(null != imageLoader)imageLoader.downLoadPic(context, url, dir, picName, callBack);
    }

    @Override
    public void clearMemory(Context context) {
        if(null != imageLoader)imageLoader.clearMemory(context);
    }

    @Override
    public void cancelAllTasks(Context context) {
        if(null != imageLoader)imageLoader.cancelAllTasks(context);
    }

    @Override
    public void resumeAllTasks(Context context) {
        if(null != imageLoader)imageLoader.resumeAllTasks(context);
    }

    @Override
    public void clearDiskCache(Context context) {
        if(null != imageLoader)imageLoader.clearDiskCache(context);
    }

    @Override
    public void cleanAll(Context context) {
        if(null != imageLoader)imageLoader.cleanAll(context);
    }

}
