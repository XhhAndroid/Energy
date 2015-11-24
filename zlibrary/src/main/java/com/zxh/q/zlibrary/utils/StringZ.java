package com.zxh.q.zlibrary.utils;

import android.widget.EditText;

/**
 * Created by Administrator on 2015/10/18.
 */
public class StringZ {
    /**
     * @param a
     * @descrip 判断字符串是否为空
     * @return
     */
    public static Boolean Isempty(String a){
        if(a != null && a.length() > 0){
            return false;
        }
        return true;
    }

    /**
     * 判断edittext是否为空
     * @param et
     * @return
     */
    public static Boolean Isempty(EditText et){
        if(et == null){
            return true;
        }
        String text = et.getText().toString().trim();
        return text.length() <= 0;
    }
}
