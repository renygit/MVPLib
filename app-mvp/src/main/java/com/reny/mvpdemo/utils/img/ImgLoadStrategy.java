package com.reny.mvpdemo.utils.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.reny.mvpdemo.utils.img.glide.DownCallBack;

/**
 * Created by admin on 2017/6/8.
 */

public interface ImgLoadStrategy {

    void disPlay(@Nullable Object model, @NonNull View view);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @NonNull Drawable error);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @DrawableRes int error);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @DrawableRes int error);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @NonNull Drawable error);

    void downLoadPic(Context context, String url, String dir, String picName, DownCallBack callBack);

    void clearMemory(Context context);
    void cancelAllTasks(Context context);
    void resumeAllTasks(Context context);
    void clearDiskCache(Context context);
    void cleanAll(Context context);

}
