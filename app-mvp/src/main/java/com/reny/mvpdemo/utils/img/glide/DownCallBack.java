package com.reny.mvpdemo.utils.img.glide;

import java.io.File;

/**
 * Created by CWJ on 2017/3/8.
 */

public interface DownCallBack {
    void onSuccess(File file);
    void onFailed(String err);
}
