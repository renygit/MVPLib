package com.reny.mvpdemo.ui.iview;

import com.reny.mvpdemo.entity.model.GankData;
import com.reny.mvpvmlib.base.IRBaseView;

/**
 * Created by reny on 2017/6/20.
 */

public interface FAView extends IRBaseView {

    void setData(GankData data, boolean isRefresh);
    void onError(Throwable e, boolean isRefresh);

}
