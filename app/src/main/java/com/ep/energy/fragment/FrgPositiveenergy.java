package com.ep.energy.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ep.energy.R;
import com.zxh.q.zlibrary.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveenergy extends BaseFragment {
    @Bind(R.id.tableLayout)
    TableLayout tableLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<String> titleContainer = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positiveenergy, container, false);

        initdata();

        ButterKnife.bind(this, layout);
        return layout;
    }

    private void initdata() {
        titleContainer.add("列表一");
        titleContainer.add("列表二");
        titleContainer.add("列表三");
        titleContainer.add("列表四");

        for (int i = 0 ; i< titleContainer.size() ; i ++) {
            String str = titleContainer.get(i);
            TextView tv = new TextView(mcontext);
            tv.setText(str);
            tableLayout.addView(tv);
        }
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
