package com.reny.mvpvmlib.http;

import android.support.annotation.Nullable;

import com.reny.mvpvmlib.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by reny on 2017/6/6.
 */

public abstract class SimpleServiceFactory<S> extends BaseServiceFactory<S> {

    private static long DEFAULT_TIMEOUT = 10;//超时时间 10秒

    @Override
    protected OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        DEFAULT_TIMEOUT = (getDefaultTimeOut() == null ? DEFAULT_TIMEOUT : getDefaultTimeOut());
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.retryOnConnectionFailure(true);

        setClientBuilder(clientBuilder);

        return clientBuilder.build();
    }

    @Nullable
    public abstract Long getDefaultTimeOut();

    public abstract void setClientBuilder(OkHttpClient.Builder clientBuilder);
}
