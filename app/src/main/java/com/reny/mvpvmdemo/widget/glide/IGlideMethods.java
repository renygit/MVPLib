package com.reny.mvpvmdemo.widget.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by admin on 2017/5/20.
 */

public interface IGlideMethods {

    interface LoadBitmapListener{
        void loadSuc(Bitmap bitmap);
    }

    void disPlay(@Nullable Object model, @NonNull View view);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @NonNull Drawable error);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @DrawableRes int error);
    void disPlay(@Nullable Object model, @NonNull View view, @NonNull Drawable placeholder, @DrawableRes int error);
    void disPlay(@Nullable Object model, @NonNull View view, @DrawableRes int placeholder, @NonNull Drawable error);

    //显示圆角图片
    void disPlayRound(@Nullable Object model, @NonNull ImageView view, int radius);

    //获取bitmap
    void loadBitmap(@Nullable Object model, @NonNull Context context, @NonNull LoadBitmapListener loadBitmapListener);

    void gcBitmap(Bitmap bitmap);
    void clearMemory(Context context);
    void cancelAllTasks(Context context);
    void resumeAllTasks(Context context);
    void clearDiskCache(Context context);
    void cleanAll(Context context);


}
