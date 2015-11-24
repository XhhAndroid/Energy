package com.ep.energy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ep.energy.R;
import com.ep.energy.bean.EnergyBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class EnergyGridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater = null;
    private List<EnergyBean> energyBeanList;

    public EnergyGridviewAdapter(Context context,List<EnergyBean> energyBeanList){
        this.context = context;
        this.energyBeanList = energyBeanList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return energyBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.energhlist_item,null);
        }
        holder.imageview = (ImageView)convertView.findViewById(R.id.imageview);
        holder.menuimg = (ImageView)convertView.findViewById(R.id.menuimg);
        return convertView;
    }
    class Holder{
        ImageView imageview;
        ImageView menuimg;
    }
}
