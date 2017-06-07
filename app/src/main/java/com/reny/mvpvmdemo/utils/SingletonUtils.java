package com.reny.mvpvmdemo.utils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.reny.mvpvmdemo.MyApplication;

import java.io.File;

/**
 * Created by admin on 2017/6/6.
 */

public class SingletonUtils {

    public static ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));
    public static File cacheDir = MyApplication.getContext().getCacheDir();

}
