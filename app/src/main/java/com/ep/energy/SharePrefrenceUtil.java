package com.ep.energy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/2.
 */

public class SharePrefrenceUtil {

    /**
     * 保存星座
     * @param constellationName
     */
    public static void setConstellation(String constellationName) {
        SharedPreferences sp = EApplication.getInstance().getSharedPreferences("energy", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("constellation_name", constellationName);
        editor.apply();
    }

    public static String getConstellation() {
        SharedPreferences sp = EApplication.getInstance().getSharedPreferences("energy", Context.MODE_PRIVATE);
        return sp.getString("constellation_name", "金牛座");
    }
    /**
     * 保存崩溃日志
     */
    public static void setOOMLog(String constellationName) {
        Set<String> defValues = getOOMLog();
        defValues.add(constellationName);
        SharedPreferences sp = EApplication.getInstance().getSharedPreferences("energy", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("oom_log", defValues);
        editor.apply();
    }
    public static Set<String> getOOMLog() {
        Set<String> defValues = new HashSet<>();
        SharedPreferences sp = EApplication.getInstance().getSharedPreferences("energy", Context.MODE_PRIVATE);
        return sp.getStringSet("oom_log", defValues);
    }
    public static void clearOOMLog(){
        Set<String> defValues = new HashSet<>();
        SharedPreferences sp = EApplication.getInstance().getSharedPreferences("energy", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("oom_log", defValues);
        editor.apply();
    }
}
