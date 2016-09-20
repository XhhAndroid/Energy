package com.ep.energy.fragment.uiInterface;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 个人中心组件 接口
 * Created by Administrator on 2016/9/20.
 */
public interface UserCenterInterface {
    void constellationImg(ImageView imageView);
    void constellation(TextView textView);
    void healthNum(TextView textView);
    void loveNum(TextView textView);
    void businessNum(TextView textView);
    void financeNum(TextView textView);
    void luckNum(TextView textView);
    void tip(TextView textView);
    void luckColorLin(LinearLayout linearLayout);
}
