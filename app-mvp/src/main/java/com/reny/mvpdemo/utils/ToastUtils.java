package com.reny.mvpdemo.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.reny.mvpdemo.MyApplication;

public class ToastUtils {

    private static Toast toast = null;

    private ToastUtils() {
    }

    public static void showShort(@StringRes int resId) {
        if(null == toast){
            toast = Toast.makeText(MyApplication.getContext(), resId, Toast.LENGTH_SHORT);
        }
        toast.setText(resId);
        toast.show();
    }


    public static void showShort(String message) {
        if(null == toast){
            toast = Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }


    public static void showLong(@StringRes int resId) {
        if(null == toast){
            toast = Toast.makeText(MyApplication.getContext(), resId, Toast.LENGTH_LONG);
        }
        toast.setText(resId);
        toast.show();
    }


    public static void showLong(String message) {
        if(null == toast){
            toast = Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_LONG);
        }
        toast.setText(message);
        toast.show();
    }
}
