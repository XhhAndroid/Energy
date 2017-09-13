package com.ep.energy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ep.energy.BaseFragment;
import com.ep.energy.R;
import com.ep.energy.activity.NewsWebActivity;
import com.ep.energy.adapter.EnergyAdapter;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.db.DbManger;
import com.ep.energy.db.model.NewsInfo;
import com.ep.energy.http.OkHttpManager;
import com.ep.energy.http.ValueParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveenergy extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.swiperefrelayout)
    SwipeRefreshLayout refreshLayout;

    private int curPage = 1;
    private int PageSize = 15;
    private boolean refresh = true;

    private EnergyAdapter energyAdapter;
    private List<PositivityModel.ResultBean.ListBean> positivityList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positiveenergy, container, false);
        ButterKnife.bind(this, layout);

        initView();
        initAdapter();

        showLoading();
//        positiveEnergyPre = new PositiveEnergyPre(this);
//        positiveEnergyPre.getPositiveEnergyData(true);
        requestPositiveData(true);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && (view.getLastVisiblePosition() + 1) == view.getCount()) {
                    LoadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        return layout;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private void initAdapter() {
        energyAdapter = new EnergyAdapter(getActivity(), this);
        energyAdapter.setList(positivityList);
        listview.setAdapter(energyAdapter);

    }

    private void requestPositiveData(final boolean fresh) {
        String url = "http://v.juhe.cn/weixin/query";
        List<ValueParam> params = new ArrayList<>();
        params.add(new ValueParam("pno", String.valueOf(curPage)));
        params.add(new ValueParam("ps", String.valueOf(PageSize)));
        params.add(new ValueParam("key", "0e86536e1934ebc5bbc1c299b0bc2093"));
        OkHttpManager.okHttpCall_POST(getActivity(), url, params, new OkHttpManager.HttpListner() {
            @Override
            public void onStart() {
                if (fresh) {
                    dialogShow();
                }
            }

            @Override
            public void onSuccess(final String response) {
                dialogMiss();
                if (fresh) {
                    refreshFinish();
                }
                Gson gson = new Gson();
                Type type = new TypeToken<PositivityModel>() {
                }.getType();
                PositivityModel positivityModel = gson.fromJson(response, type);
                if (positivityModel != null) {
                    if (positivityModel.getError_code() == 0) {
                        suceessData(positivityModel);
                    }
                }
            }

            @Override
            public void onFail(String message) {
                dialogMiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick0(View v, Object o, int position) {
        super.onClick0(v, o, position);
        if (o != null) {
            PositivityModel.ResultBean.ListBean listBean = (PositivityModel.ResultBean.ListBean) o;
            startActivity(new Intent(getActivity(), NewsWebActivity.class)
                    .putExtra(NewsWebActivity.URL, listBean.getUrl())
                    .putExtra(NewsWebActivity.TITLE, listBean.getTitle()));
        }
    }

    public void LoadMore() {
        refresh = false;
        curPage++;
        requestPositiveData(false);
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        refresh = true;
        requestPositiveData(true);
    }


    public void dialogShow() {
        showLoading();
    }

    public void dialogMiss() {
        dissmissLoading();
    }

    public void refreshFinish() {
        refreshLayout.setRefreshing(false);
    }

    int addPosition = 8;
    CopyOnWriteArrayList<NewsInfo> newsInfoList = new CopyOnWriteArrayList<>();

    public void suceessData(PositivityModel positivityModel) {
        if (positivityModel.getError_code() == 0) {
            if (refresh) {
                addPosition = 8;
                newsInfoList.clear();
                newsInfoList.addAll(DbManger.findNewsInfoAll());
                positivityList.clear();
                if (energyAdapter != null)
                    energyAdapter.notifyDataSetChanged();
            }
            positivityList.addAll(positivityModel.getResult().getList());
            for (NewsInfo newsInfo : newsInfoList) {
                PositivityModel.ResultBean.ListBean listBean = new PositivityModel.ResultBean.ListBean();
                listBean.setFirstImg(newsInfo.getThumbnail());
                listBean.setTitle(newsInfo.getNewsTitle());
                listBean.setSource("Energy");
                listBean.setUrl(newsInfo.getNewsUrl());
                if (positivityList.size() > addPosition) {
                    positivityList.add(addPosition, listBean);
                    addPosition *= 2;
                    newsInfoList.remove(newsInfo);
                }
            }
            if (energyAdapter != null) {
                energyAdapter.notifyDataSetChanged();
            }
        }
    }
}
