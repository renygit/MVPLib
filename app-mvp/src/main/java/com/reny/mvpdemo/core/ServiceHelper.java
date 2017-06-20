package com.reny.mvpdemo.core;

import com.reny.mvpdemo.api.DoubanApiService;
import com.reny.mvpdemo.api.GankApiService;

/**
 * Created by reny on 2017/2/9.
 */

public class ServiceHelper {

    public static GankApiService getGankAS(){
        return (GankApiService) ServiceFactory.getInstance().getService(GankApiService.class);
    }


    public static DoubanApiService getDoubanAS(){
        return (DoubanApiService) ServiceFactory.getInstance().getService(DoubanApiService.class);
    }

}
