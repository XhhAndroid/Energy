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
public class Eaplication extends Application {
    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        initRequest();
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        Request request = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .build();
        Call call = okHttpClient.newCall(request);

//        RequestEg(call);
    }

    /**get请求*/
    private void RequestEg(Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
