package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ep.energy.BaseFragment;
import com.ep.energy.R;
import com.ep.energy.adapter.HistoryTodayAdapter;
import com.ep.energy.bean.HistoryTodayModel;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.ep.energy.interfaces.imterfaceImp.BaseAdapterListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Created by Administrator on 2015/10/24.
 */
public class FrgPositiveHistory extends BaseFragment {
    @Bind(R.id.listview)
    ListView listview;

    protected WeakReference<View> mRootView;

    private HistoryTodayAdapter historyTodayAdapter;
    private List<HistoryTodayModel.HistoryEntity> historyEntities = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.e_positive_history_today, container,
                    false);
            mRootView = new WeakReference<View>(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        ButterKnife.bind(this, mRootView.get());

        initview();
        historyToday();

        return mRootView.get();
    }

    private void initview() {
        historyTodayAdapter = new HistoryTodayAdapter(new BaseAdapterListner<HistoryTodayModel.HistoryEntity>() {
            @Override
            public void onClick0(View v, HistoryTodayModel.HistoryEntity historyEntity, int position) {
                super.onClick0(v, historyEntity, position);
            }
        }, getActivity());
        historyTodayAdapter.setList(historyEntities);
        listview.setAdapter(historyTodayAdapter);
    }

    private void historyToday() {
        String url = "http://route.showapi.com/119-42";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("showapi_appid", "55271"));
        params.add(new ValueParam("showapi_sign", "a4c7de874ee5466a93c9d19180550374"));
        params.add(new ValueParam("showapi_timestamp", ""));
        OkHttpManager.okHttpCall_POST(getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(final String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<HistoryTodayModel>() {
                }.getType();
                HistoryTodayModel historyTodayModel = gson.fromJson(response, type);
                if (historyTodayModel != null && historyTodayModel.getShowapi_res_body() != null) {
                    if (historyTodayModel.getShowapi_res_code() == 0) {
                        suceessData(historyTodayModel);
                    }
                }
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    private void suceessData(HistoryTodayModel positivityModel) {
        historyEntities.addAll(positivityModel.getShowapi_res_body().getList());
        historyTodayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
