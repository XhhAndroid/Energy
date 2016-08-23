package com.ep.energy;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 父fragment
 * Created by Administrator on 2015/10/24.
 */
public class BaseFragment extends Fragment{
    private View view;
    public Context mcontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mcontext = getActivity();
        return view;
    }
}
