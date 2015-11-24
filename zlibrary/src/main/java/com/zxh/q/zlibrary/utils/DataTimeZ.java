package com.zxh.q.zlibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/31.
 */
public class DataTimeZ {
    public static String getCurrentTime(){
        Date data = new Date();
        SimpleDateFormat current = new SimpleDateFormat("MM-dd HH:mm:ss");
        return current.format(data);
    }
}
