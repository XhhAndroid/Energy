package com.ep.energy.fragment.presenter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ep.energy.BaseFragment;
import com.ep.energy.bean.UserCenterBean;
import com.ep.energy.fragment.uiInterface.UserCenterInterface;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public class UserCenterPresenter implements UserCenterInterface {
    private BaseFragment context;
    private UserCenterBean userCenterBean = null;

    public UserCenterPresenter(BaseFragment context) {
        this.context = context;

        getConstellationData();
    }

    /**
     * 请求数据
     */
    public void getConstellationData() {
        final String url = "http://web.juhe.cn:8080/constellation/getAll";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("consName", "金牛座"));
        params.add(new ValueParam("type", "tomorrow"));
        params.add(new ValueParam("key", "99fc47fd03231baa9d6741253dc63e09"));
        OkHttpManager.okHttpCall_POST(context.getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<UserCenterBean>() {
                }.getType();
                UserCenterBean centerBean = gson.fromJson(response, type);
                if (centerBean != null) {
                    if (centerBean.getError_code() == 0) {
                        userCenterBean = centerBean;
                    }
                }
            }

            @Override
            public void onFail(String message) {

            }
        });

    }

    @Override
    public void constellationImg(ImageView imageView) {

    }

    @Override
    public void constellation(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText(userCenterBean.getName());
    }

    @Override
    public void healthNum(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText("健康指数:" + userCenterBean.getHealth());
    }

    @Override
    public void loveNum(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText("爱情指数:" + userCenterBean.getLove());
    }

    @Override
    public void businessNum(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText("事业指数:" + userCenterBean.getWork());
    }

    @Override
    public void financeNum(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText("财运指数:" + userCenterBean.getMoney());
    }

    @Override
    public void luckNum(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText("幸运数字:" + userCenterBean.getSummary());
    }

    @Override
    public void tip(TextView textView) {
        if (userCenterBean == null)
            return;
        textView.setText(userCenterBean.getHealth());
    }

    @Override
    public void luckColorLin(LinearLayout linearLayout) {
    }
}
