package com.reny.mvpvmdemo;

import android.app.Application;
import android.content.Context;

import com.reny.mvpvmdemo.utils.img.ImageUtils;
import com.reny.mvpvmdemo.utils.img.glide.GlideLoadStrategy;
import com.reny.mvpvmlib.utils.LogUtils;
import com.renygit.multistateview.MultiStateConfig;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zhy.changeskin.SkinManager;

/**
 * Created by reny on 2017/6/6.
 */

public class MyApplication extends Application {

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
    }

    private void init() {
        //AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        ImageUtils.getInstance().init(new GlideLoadStrategy());
        LogUtils.init(BuildConfig.DEBUG);
        SkinManager.getInstance().init(this);
        LogUtils.isPrintResponseData = BuildConfig.DEBUG;//是否打印网络返回的源数据
        LogUtils.isPrintResponseData = false;


        //设置全局的多状态配置 局部支持xml设置 可以设置不同状态的图片提示
        // 还有。。。自己看源码，如果喜欢这个库 可以提建议给我  虽然我不一定会改└(^o^)┘
        MultiStateConfig.getInstance().setConfig(
                new MultiStateConfig.Build()
                        .setTipEmpty("没有相关数据，点击重试")
                        .setTipError("加载失败，点击重试")
                        .setTipNoNetwork("没有网络，点击重试")
                        .setIndicatorName("BallSpinFadeLoaderIndicator")
                        .setIndicatorColor(R.color.colorAccent)
        );

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //指定Header
                return new MaterialHeader(context).setColorSchemeColors(0xff000000);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定Footer
                return new ClassicsFooter(context);
            }
        });
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
