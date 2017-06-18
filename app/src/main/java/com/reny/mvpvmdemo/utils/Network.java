package com.reny.mvpvmdemo.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.reny.mvpvmdemo.MyApplication;

import java.lang.reflect.Method;

/**
 * 跟网络相关的工具类
 */
public class Network {

    private static final String TAG = Network.class.getSimpleName();

    private Network() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static boolean isConnected() {
        NetworkInfo net = getConnectivityManager(MyApplication.getContext()).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    public static boolean isConnectedOrConnecting() {
        ConnectivityManager connectivity = (ConnectivityManager)
                MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo nInfo=connectivity.getActiveNetworkInfo();

            if (nInfo != null && nInfo.getState()== NetworkInfo.State.CONNECTED) {
               return  true;
            }
        }
        return false;
    }

    public static NetType getConnectedType(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        if(net != null) {
            switch(net.getType()) {
                case 0:
                    return NetType.Mobile;
                case 1:
                    return NetType.Wifi;
                default:
                    return NetType.Other;
            }
        } else {
            return NetType.None;
        }
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 1 && net.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 0 && net.isConnected();
    }

    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || isMobileAvailable(context) && isMobileEnabled(context);
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if(nets != null) {
            NetworkInfo[] arr$ = nets;
            int len$ = nets.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                NetworkInfo net = arr$[i$];
                if(net.getType() == 1) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if(nets != null) {
            NetworkInfo[] arr$ = nets;
            int len$ = nets.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                NetworkInfo net = arr$[i$];
                if(net.getType() == 0) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isMobileEnabled(Context context) {
        try {
            Method e = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            e.setAccessible(true);
            return ((Boolean)e.invoke(getConnectivityManager(context), new Object[0])).booleanValue();
        } catch (Exception var2) {
            var2.printStackTrace();
            return true;
        }
    }

    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            NetworkInfo in = connectivity.getActiveNetworkInfo();
            Log.i(TAG, "-------------$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$-------------");
            Log.i(TAG, "getActiveNetworkInfo: " + in);
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if(info != null) {
                for(int i = 0; i < info.length; ++i) {
                    Log.i(TAG, "NetworkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    Log.i(TAG, "NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    Log.i(TAG, "NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    Log.i(TAG, "NetworkInfo[" + i + "]: " + info[i]);
                }

                Log.i(TAG, "\n");
            } else {
                Log.i(TAG, "getAllNetworkInfo is null");
            }
        }

        return false;
    }

    public static int getConnectedTypeINT(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        if(net != null) {
            Log.i(TAG, "NetworkInfo: " + net.toString());
            return net.getType();
        } else {
            return -1;
        }
    }

    public static int getTelNetworkTypeINT(Context context) {
        return getTelephonyManager(context).getNetworkType();
    }

    public static NetWorkType getNetworkType(Context context) {
        int type = getConnectedTypeINT(context);
        switch(type) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                int teleType = getTelephonyManager(context).getNetworkType();
                switch(teleType) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return NetWorkType.Net2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return NetWorkType.Net3G;
                    case 13:
                        return NetWorkType.Net4G;
                    default:
                        return NetWorkType.UnKnown;
                }
            case 1:
                return NetWorkType.Wifi;
            default:
                return NetWorkType.UnKnown;
        }
    }

    public enum NetWorkType {
        UnKnown(-1),
        Wifi(1),
        Net2G(2),
        Net3G(3),
        Net4G(4);

        public int value;

        private NetWorkType(int value) {
            this.value = value;
        }
    }

    public enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        public int value;

        NetType(int value) {
            this.value = value;
        }
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

}
