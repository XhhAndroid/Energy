package com.ep.energy.modelInterface.imple;

import com.ep.energy.BaseFragment;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.ep.energy.modelInterface.PositiveEnergyModelListner;
import com.ep.energy.modelInterface.getEnergyListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaohui on 2016/9/22.
 */

public class getEnergyImple implements getEnergyListner {

    @Override
    public void getEnergyData(BaseFragment baseFragment, final boolean fresh, int curPage, int PageSize, final PositiveEnergyModelListner positiveEnergyView) {
        String url = "http://v.juhe.cn/weixin/query";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("pno", String.valueOf(curPage)));
        params.add(new ValueParam("ps", String.valueOf(PageSize)));
        params.add(new ValueParam("key", "0e86536e1934ebc5bbc1c299b0bc2093"));
        OkHttpManager.okHttpCall_POST(baseFragment.getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {
                if(fresh){
                    positiveEnergyView.dialogShow();
                }
            }

            @Override
            public void onSuccess(final String response) {
                positiveEnergyView.dialogMiss();
                if (fresh) {
                    positiveEnergyView.refreshFinish();
                }
                Gson gson = new Gson();
                Type type = new TypeToken<PositivityModel>() {
                }.getType();
                PositivityModel positivityModel = gson.fromJson(response, type);
                if (positivityModel != null) {
                    if (positivityModel.getError_code() == 0) {
                        positiveEnergyView.suceessData(positivityModel);
                    }
                }
            }

            @Override
            public void onFail(String message) {
                positiveEnergyView.dialogMiss();
            }
        });
    }
}
