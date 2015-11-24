package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ep.energy.R;
import com.ep.energy.adapter.EnergyGridviewAdapter;
import com.ep.energy.bean.EnergyBean;
import com.zxh.q.zlibrary.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveenergy extends BaseFragment {
    private GridView gridView;
    private EnergyGridviewAdapter adapter;
    private List<EnergyBean> energyBeanList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positiveenergy,container,false);

        initview(layout);
        initdata();

        return layout;
    }

    private void initdata() {
        adapter = new EnergyGridviewAdapter(getActivity(),energyBeanList);
        gridView.setAdapter(adapter);

        for (int i = 0; i < 17;i ++){
            EnergyBean energyBean = new EnergyBean();
            energyBeanList.add(energyBean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initview(View view) {
        gridView = (GridView)view.findViewById(R.id.gridview);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
