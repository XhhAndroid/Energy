package com.zxh.q.zlibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jkt-yf-002on 2015/9/18.
 */

public class ToastZ {
    private static Toast toast = null;


    public static void Toastz(Context context, String a) {
        if (!StringZ.Isempty(a)) {
            toast = Toast.makeText(context, a, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
