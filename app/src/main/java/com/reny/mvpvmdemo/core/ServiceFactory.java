package com.reny.mvpvmdemo.core;

import android.support.annotation.Nullable;

import com.reny.mvpvmdemo.BuildConfig;
import com.reny.mvpvmdemo.MyApplication;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.api.APIConfig;
import com.reny.mvpvmdemo.api.MyNetInterceptor;
import com.reny.mvpvmdemo.utils.SingletonUtils;
import com.reny.mvpvmlib.http.BaseServiceFactory;
import com.reny.mvpvmlib.http.SimpleServiceFactory;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by reny on 2017/2/9.
 */

public class ServiceFactory<S> extends SimpleServiceFactory<S> {

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Nullable
    @Override
    public Long getDefaultTimeOut() {
        return null;
    }

    @Override
    public void setClientBuilder(OkHttpClient.Builder clientBuilder) {
        clientBuilder.cookieJar(SingletonUtils.cookieJar);
        File cacheFile = new File(SingletonUtils.cacheDir, "HttpCache"); // 指定缓存路径
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
        clientBuilder.cache(cache);

        clientBuilder.addNetworkInterceptor(new MyNetInterceptor());
    }

    @Override
    public String getDefaultBaseUrl() {
        //默认BaseUrl
        return APIConfig.BASE_URL_DEFAULT;
    }
}
