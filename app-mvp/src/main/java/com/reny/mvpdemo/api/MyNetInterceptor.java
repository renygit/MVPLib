package com.reny.mvpdemo.api;

import com.reny.mvpvmlib.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by reny on 2017/6/7.
 * 在这里可以对请求和响应做统一处理
 */

public class MyNetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        LogUtils.e(request.url().url().toString());//打印要访问的地址 可注释

        return chain.proceed(request);
    }
}
