package com.ep.energy;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ep.energy.adapter.EBaseAdapter;
import com.ep.energy.dialog.LoadingDialog;

/**
 * 父fragment
 * Created by Administrator on 2015/10/24.
 */
public class BaseFragment extends Fragment implements EBaseAdapter.OnclickListner {
    private View view;
    public Context mContext;
    public LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        loadingDialog = new LoadingDialog(getActivity());
        return view;
    }
    public void showLoading(){
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(getActivity());
        }
        if(loadingDialog.isShowing()){
            return;
        }
        loadingDialog.show();
    }
    public void dissmissLoading(){
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(getActivity());
        }
        if(!loadingDialog.isShowing()){
            return;
        }
        loadingDialog.dismiss();
    }

    @Override
    public void onClick0(View v, Object o, int position) {

    }

    @Override
    public void OnClick1(View v, Object o, int position) {

    }

    @Override
    public void Onclick2(View v, Object o, int position) {

    }

    @Override
    public void LoadMore() {

    }
}
