package com.ep.energy.jumpUtils;

import android.content.Context;
import android.content.Intent;

import com.ep.energy.activity.NewsWebActivity;

import java.util.Map;

/**
 * Created by zhangxiaohui on 2017/9/18.
 */

public class JumpUtil {
    public static final String ToActivity = "toActivity";
    public static final String WebNews = "webNews";


    public static void ToActivity(Context mContext, Map<String, String> jumpMap) {
        if (jumpMap == null) {
            return;
        }
        Intent intent = new Intent();
        if (jumpMap.containsKey(ToActivity)) {
            if (jumpMap.get(ToActivity).equals(WebNews)) {
                intent.setClass(mContext, NewsWebActivity.class);
                if (jumpMap.containsKey(NewsWebActivity.URL)) {
                    intent.putExtra(NewsWebActivity.URL, jumpMap.get(NewsWebActivity.URL));
                }
                if (jumpMap.containsKey(NewsWebActivity.TITLE)) {
                    intent.putExtra(NewsWebActivity.TITLE, jumpMap.get(NewsWebActivity.TITLE));
                }
            }
        }
        mContext.startActivity(intent);
    }
}
