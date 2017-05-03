package com.ep.energy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/2.
 */

public class SharePrefrenceUtil {

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
}
