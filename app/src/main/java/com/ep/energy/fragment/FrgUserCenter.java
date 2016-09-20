package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ep.energy.BaseFragment;
import com.ep.energy.R;
import com.zxh.q.zlibrary.utils.ToastZ;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgUserCenter extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_usercenter, container, false);
        ButterKnife.bind(this, layout);

        initview();

        return layout;
    }

    private void initview() {

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
