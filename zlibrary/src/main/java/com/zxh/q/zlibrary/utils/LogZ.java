package com.zxh.q.zlibrary.utils;

import android.util.Log;

/**
 * Created by Administrator on 2015/10/18.
 */
public class LogZ {
    private static final String TAG = "com.ep";
    public static void i(String i){
        if (!StringZ.Isempty(i)){
            Log.i(TAG,i);
        }
    }
    public static void e(String e){
        if (!StringZ.Isempty(e)){
            Log.e(TAG,e);
        }
    }
}
