package com.ep.energy;

import android.app.Application;
import android.content.Context;

import com.dgs.kfi.av;
import com.ep.energy.crash.CrashHandler;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;
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
    public boolean isAlive = false;

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
        initPush();
        UMShareAPI.get(this);

        LitePal.initialize(this);
        LitePal.getDatabase();
        isAlive = true;

        //三个参数依次代表内插屏，外插屏，应用外退弹
//        av.mbrgh(this, true, true, true);
//        av.jgcl(this, jiange, yanchi);//jiange外插屏间隔,单位是分钟,最小为2;yanchi外插屏首次延迟时间,单位是秒,最小为0.
        // 默认间隔2分钟,无延迟.
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 程序终止的时候执行
        isAlive = false;
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level);
        isAlive = false;
    }

    private void initPush() {
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
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
