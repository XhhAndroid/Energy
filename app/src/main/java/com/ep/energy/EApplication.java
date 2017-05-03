package com.ep.energy;

import android.app.Application;
import android.content.Context;

import com.ep.energy.crash.CrashHandler;

import okhttp3.OkHttpClient;

/**
 * application
 * Created by zhangxiaohui on 2016-06-11.
 */
public class EApplication extends Application {
    public static OkHttpClient okHttpClient;
    private static EApplication eApplication;
    private Context context;
    protected CrashHandler crashHandler;

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

        crashHandler = CrashHandler.getInstance();
        crashHandler.init(true, false);
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}
