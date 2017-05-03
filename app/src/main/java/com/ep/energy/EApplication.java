package com.ep.energy;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * application
 * Created by zhangxiaohui on 2016-06-11.
 */
public class EApplication extends Application {
    public static OkHttpClient okHttpClient;
    private static EApplication eApplication;
    private Context context;

    public EApplication() {}

    public static EApplication getInstance() {
        return eApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(null == context){
            context = getApplicationContext();
        }
        eApplication = this;
        initRequest();
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}
