package com.zxh.q.zlibrary.utils;

import android.content.Context;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by jkt-yf-002 on 2015/11/23.
 */
public class Zscreen {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidthDip(Context context) {
        int widthPx = Zscreen.getScreenWidth(context);
        int widthDip = Zscreen.px2dip(context, widthPx);
        return widthDip;
    }

    public static int getScreenHeightDip(Context context) {
        int heightPx = Zscreen.getScreenHeight(context);
        int heightDip = Zscreen.px2dip(context, heightPx);
        return heightDip;
    }

    /**
     * 获取设备密度
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().density;
        } else {
            return 1.0f;
        }
    }

    /**
     * 获取设备密度DPI
     *
     * @param context
     * @return
     */
    public static int getDensityDpi(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().densityDpi;
        } else {
            return 120;
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        } else {
            return 0;
        }
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        } else {
            return 0;
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获得触发移动事件的最短距离
     * @param context
     * @return
     */
    public static float getTouchSlop(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        return configuration.getScaledTouchSlop();
    }
}
