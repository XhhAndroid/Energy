package com.ep.energy.fragment.presenter;

import com.ep.energy.BaseFragment;
import com.ep.energy.adapter.EnergyAdapter;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.fragment.uiInterface.PositiveenergyInterface;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxh.q.zlibrary.circlerefresh.CircleRefreshLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaohui on 2016/9/14.
 */
public class PositiveenergyPresenter implements PositiveenergyInterface {
    private BaseFragment context;
    private CircleRefreshLayout refreshLayout;
    private EnergyAdapter energyAdapter;
    private List<PositivityModel.ResultBean.ListBean> positivityList = null;

    public PositiveenergyPresenter(EnergyAdapter energyAdapter, BaseFragment context, CircleRefreshLayout refreshLayout, List<PositivityModel.ResultBean.ListBean> positivityList) {
        this.context = context;
        this.refreshLayout = refreshLayout;
        this.energyAdapter = energyAdapter;
        this.positivityList = positivityList;
    }

    @Override
    public void setAdapterData(boolean fresh, int curPage, int PageSize) {

        getEnergyData(fresh, curPage, PageSize);
    }

    private void getEnergyData(final boolean fresh, int curPage, int PageSize) {
        String url = "http://v.juhe.cn/weixin/query";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("pno", String.valueOf(curPage)));
        params.add(new ValueParam("ps", String.valueOf(PageSize)));
        params.add(new ValueParam("key", "0e86536e1934ebc5bbc1c299b0bc2093"));
        OkHttpManager.okHttpCall_POST(context.getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(final String response) {
                context.dissmissLoading();
                if (fresh && refreshLayout != null) {
                    refreshLayout.finishRefreshing();
                }
                Gson gson = new Gson();
                Type type = new TypeToken<PositivityModel>() {
                }.getType();
                PositivityModel positivityModel = gson.fromJson(response, type);
                if (positivityModel != null) {
                    if (fresh) {
                        positivityList.clear();
                        if (energyAdapter != null)
                            energyAdapter.notifyDataSetChanged();
                    }
                    if (positivityModel.getError_code() == 0) {
                        positivityList.addAll(positivityModel.getResult().getList());
                        if (energyAdapter != null)
                            energyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(String message) {
                context.dissmissLoading();
            }
        });
    }
}
