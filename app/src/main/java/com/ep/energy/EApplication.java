package com.ep.energy;

import android.app.Application;
import android.content.Context;

import com.ep.energy.crash.CrashHandler;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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

        initShare();
        UMShareAPI.get(this);
    }
    private void initShare(){
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106393142", "jfHUwyz1pPjDO4aJ");
        PlatformConfig.setSinaWeibo("172945907", "58a9a7c97cc11c1bbd1d83672c9f8429", "http://sns.whalecloud.com");
    }

    private void initRequest() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}
