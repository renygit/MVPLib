package com.reny.mvpvmlib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by admin on 2017/6/6.
 */

public class GsonSingleton {

    public static final Gson gson = new GsonBuilder().setLenient().create();

}
