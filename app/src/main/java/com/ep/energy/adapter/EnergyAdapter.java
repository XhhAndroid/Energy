package com.ep.energy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ep.energy.R;
import com.ep.energy.bean.PositivityModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/27.
 */
public class EnergyAdapter extends EBaseAdapter<PositivityModel.ResultBean.ListBean> {

    public EnergyAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.energhlist_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        PositivityModel.ResultBean.ListBean positivityModel = (PositivityModel.ResultBean.ListBean)getItem(position);
        if(positivityModel != null){
            holder.positiveSor.setText("来自:"+positivityModel.getSource());
            holder.positiveTitle.setText(positivityModel.getTitle());
        }
        if (positivityModel != null) {
            Glide.with(mContext)
            .load(positivityModel.getUrl())
            .into(holder.positiveImg);
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.positiveImg)
        ImageView positiveImg;
        @Bind(R.id.positiveTitle)
        TextView positiveTitle;
        @Bind(R.id.positiveSor)
        TextView positiveSor;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
