package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ep.energy.R;
import com.zxh.q.zlibrary.BaseFragment;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveMap extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positivemap,container,false);

        initview(layout);

        return layout;
    }

    private void initview(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
