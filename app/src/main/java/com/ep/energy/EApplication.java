package com.ep.energy;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * application
 * Created by zhangxiaohui on 2016-06-11.
 */
public class EApplication extends Application {
    public static OkHttpClient okHttpClient;
    private static Context context;
    private static EApplication eApplication;

    public EApplication() {}

    public static EApplication getInstance() {
        if(eApplication == null){
            eApplication = new EApplication();
        }
        return eApplication;
    }

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getContext();
        initRequest();
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}
