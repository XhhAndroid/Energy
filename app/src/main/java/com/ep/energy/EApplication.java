package com.ep.energy;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();

        initRequest();
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}
