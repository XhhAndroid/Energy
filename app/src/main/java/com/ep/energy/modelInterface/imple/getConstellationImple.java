package com.ep.energy.modelInterface.imple;

import com.ep.energy.BaseFragment;
import com.ep.energy.bean.UserCenterBean;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.ep.energy.modelInterface.GetConstellationListner;
import com.ep.energy.modelInterface.UserCenterViewInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaohui on 2016/9/21.
 */
public class getConstellationImple implements GetConstellationListner {

    @Override
    public void getConstellationData(BaseFragment baseFragment, String consname, String type, final UserCenterViewInterface userCenterViewInterface) {
        final String url = "http://web.juhe.cn:8080/constellation/getAll";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("consName", consname));
        params.add(new ValueParam("type", type));
        params.add(new ValueParam("key", "99fc47fd03231baa9d6741253dc63e09"));
        OkHttpManager.okHttpCall_POST(baseFragment.getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {
                if (userCenterViewInterface != null)
                    userCenterViewInterface.startLoading();
            }

            @Override
            public void onSuccess(String response) {
                if (userCenterViewInterface != null)
                    userCenterViewInterface.success();
                Gson gson = new Gson();
                Type type = new TypeToken<UserCenterBean>() {
                }.getType();
                UserCenterBean centerBean = gson.fromJson(response, type);
                if (centerBean != null) {
                    if (centerBean.getError_code() == 0) {
                        if (userCenterViewInterface != null)
                            userCenterViewInterface.successData(centerBean);
                    }
                }
            }

            @Override
            public void onFail(String message) {
                if (userCenterViewInterface != null)
                    userCenterViewInterface.fail();
            }
        });
    }
}
