package com.reny.mvpvmlib.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by reny on 2017/6/5.
 * 对Logger包装一次 方便切换别的Log库
 */

public class LogUtils {

    public static boolean isPrintResponseData = true;

    //更多查看：https://github.com/orhanobut/logger
    public static void init(final boolean isDebug){
        /*FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));*/
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return isDebug;
            }
        });
    }
    /**
     * 错误信息
     */
    public static void e(String msg, Object... obj) {
        Logger.e(msg, obj);
    }

    /**
     * 警告信息
     */
    public static void w(String msg, Object... obj) {
        Logger.w(msg, obj);
    }

    /**
     * 调试信息
     */
    public static void d(Object obj) {
        Logger.d(obj);
    }
    public static void d(String msg, Object... obj) {
        Logger.d(msg, obj);
    }

    /**
     * 提示信息
     */
    public static void i(String msg, Object... obj) {
        Logger.i(msg, obj);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void e(Throwable e) {
        Logger.e(e.getMessage());
    }
}
