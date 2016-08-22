package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ep.energy.R;
import com.ep.energy.adapter.EnergyAdapter;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxh.q.zlibrary.BaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveenergy extends BaseFragment {
    @Bind(R.id.listview)
    ListView listview;

    private int curPage = 1;
    private int PageSize = 15;

    private EnergyAdapter energyAdapter;
    private List<PositivityModel.ResultBean.ListBean> positivityList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positiveenergy, container, false);
        ButterKnife.bind(this, layout);

        getEnergyData(true);
        initAdapter();

        return layout;
    }

    private void initAdapter() {
        energyAdapter = new EnergyAdapter(getActivity());
        energyAdapter.setList(positivityList);
        listview.setAdapter(energyAdapter);
    }

    /**
     * 获取数据
     */
    private void getEnergyData(final boolean fresh) {
        String url = "http://v.juhe.cn/weixin/query";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("pno", String.valueOf(curPage)));
        params.add(new ValueParam("ps", String.valueOf(PageSize)));
        params.add(new ValueParam("key", ""));
        OkHttpManager.okHttpCall_POST(url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<PositivityModel>() {
                }.getType();
                PositivityModel positivityModel = gson.fromJson(response, type);
                if (positivityModel != null) {
                    if (fresh) {
                        positivityList.clear();
                        energyAdapter.notifyDataSetChanged();
                    }
                    if (positivityModel.getError_code() == 0) {
                        positivityList.addAll(positivityModel.getResult().getList());
                        energyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
