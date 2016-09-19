package com.ep.energy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ep.energy.BaseFragment;
import com.ep.energy.R;
import com.ep.energy.activity.NewsWebActivity;
import com.ep.energy.adapter.EnergyAdapter;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.fragment.presenter.PositiveenergyPresenter;
import com.ep.energy.fragment.uiInterface.PositiveenergyInterface;

import java.util.ArrayList;
import java.util.List;

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

    private EnergyAdapter energyAdapter;
    private List<PositivityModel.ResultBean.ListBean> positivityList = new ArrayList<>();

    private PositiveenergyInterface positiveenergyInterface = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positiveenergy, container, false);
        ButterKnife.bind(this, layout);

        initView();
        initAdapter();

        showLoading();
        positiveenergyInterface = new PositiveenergyPresenter(energyAdapter, FrgPositiveenergy.this, refreshLayout, positivityList);
        positiveenergyInterface.setAdapterData(true, curPage, PageSize);

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

    @Override
    public void LoadMore() {
        super.LoadMore();
        curPage++;
        positiveenergyInterface.setAdapterData(true, curPage, PageSize);
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        positiveenergyInterface.setAdapterData(true, curPage, PageSize);
    }
}
