package com.ep.energy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

    public EnergyAdapter( Context mContext,OnclickListner onclickListner) {
        super(onclickListner, mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        super.getView(position,convertView,parent);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.energhlist_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final PositivityModel.ResultBean.ListBean positivityModel = (PositivityModel.ResultBean.ListBean) getItem(position);
        if (positivityModel != null) {
            holder.positiveSor.setText("来自:" + positivityModel.getSource());
            holder.positiveTitle.setText(positivityModel.getTitle());

            Glide.with(mContext)
                    .load(positivityModel.getFirstImg())
                    .placeholder(R.drawable.energy_default_img)
                    .crossFade()
                    .into(holder.positiveImg);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickListner.onClick0(v,positivityModel,position);
            }
        });

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
