package com.zxh.q.zlibrary.view;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Administrator on 2015/10/11.
 */
public class Util {
    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }
}
