package com.reny.mvpvmlib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by reny on 2017/6/5.
 */

public interface IBaseView {
    Activity getActivity();

    void startActivity(Intent intent);

    void finish();
}
