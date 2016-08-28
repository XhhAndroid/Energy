package com.ep.energy.http;

import android.app.Activity;

import com.ep.energy.EApplication;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求管理类
 * Created by zhangxiaohui on 2016/8/22.
 */
public class OkHttpManager {

    /**
     * okhttp  GET请求
     *
     * @param url
     */
    public static void okHttpCall_GET(String url, final HttpListner httpListner) {
        Request okHttpRequest = new Request.Builder()
                .url(url)
                .build();
        Call call = EApplication.okHttpClient.newCall(okHttpRequest);
        if (httpListner != null) {
            httpListner.onStart();
        }
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (httpListner != null) {
                    httpListner.onFail(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (httpListner != null) {
                    httpListner.onSuccess(response.body().string());
                }
            }
        });
    }

    /**
     * okhttp POST请求
     *
     * @param url
     * @param valueParamList
     */
    public static void okHttpCall_POST(final Activity activity, String url, List<ValueParam> valueParamList, final HttpListner httpListner) {
        FormBody requestBody = getValueParam(valueParamList);
        Request okHttpRequest = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = EApplication.okHttpClient.newCall(okHttpRequest);
        if (httpListner != null) {
            httpListner.onStart();
        }
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (httpListner != null) {
                            httpListner.onFail(e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (httpListner != null) {
                            httpListner.onSuccess(res);
                        }
                    }
                });
            }
        });
    }

    private static FormBody getValueParam(List<ValueParam> valueParamList) {
        FormBody.Builder builder = new FormBody.Builder();
        if (valueParamList != null && valueParamList.size() > 0) {
            for (ValueParam valueParam : valueParamList) {
                builder.add(valueParam.getName(), valueParam.getValue());
            }
        }
        return builder.build();
    }

    public interface HttpListner {
        void onStart();

        void onSuccess(String response);

        void onFail(String message);
    }
}
