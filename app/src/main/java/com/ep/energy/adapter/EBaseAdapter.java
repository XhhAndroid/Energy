package com.ep.energy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhangxiaohui on 2016/8/22.
 */
public class EBaseAdapter<T> extends BaseAdapter{
    public Context mContext;
    public LayoutInflater inflater = null;
    public List<T> dataList;
    public OnclickListner onclickListner;

    public EBaseAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public EBaseAdapter(OnclickListner<T> onclickListner, Context mContext) {
        this.onclickListner = onclickListner;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }
    public void addItem(T item) {
        dataList.add(item);
    }
    public void setList(List<T> dataList){
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    public interface OnclickListner<T>{
        void onClick0(View v,T t,int position);
        void OnClick1(View v, T t, int position);
        void Onclick2(View v,T t,int position);
    }
}
