package com.ep.energy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ep.energy.R;
import com.ep.energy.bean.HistoryTodayModel;
import com.ep.energy.bean.PositivityModel;
import com.ep.energy.interfaces.imterfaceImp.BaseAdapterListner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Created by zhangxiaohui on 2018/1/26.
 */

public class HistoryTodayAdapter extends EBaseAdapter<HistoryTodayModel.HistoryEntity>{

    public HistoryTodayAdapter(BaseAdapterListner<HistoryTodayModel.HistoryEntity> onclickListner, Context mContext) {
        super(onclickListner, mContext);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.energhlist_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HistoryTodayModel.HistoryEntity positivityModel = (HistoryTodayModel.HistoryEntity) getItem(position);
        if (positivityModel != null) {
            holder.positiveSor.setText(positivityModel.getYear()+positivityModel.getMonth()+positivityModel.getDay());
            holder.positiveTitle.setText(positivityModel.getTitle());

            if(holder.positiveImg.getTag() == null || !positivityModel.getImg().equals(holder.positiveImg.getTag(R.id.imageUrlTag))){
                Glide.with(mContext)
                        .load(positivityModel.getImg())
                        .placeholder(R.drawable.energy_default_img)
                        .crossFade()
                        .animate(AnimationUtils.loadAnimation(mContext, R.anim.load_img_animal))
                        .into(holder.positiveImg);
            }
            holder.positiveImg.setTag(R.id.imageUrlTag,positivityModel.getImg());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseAdapterListner.onClick0(v, positivityModel, position);
            }
        });
        return convertView;
    }

    class ViewHolder {
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
