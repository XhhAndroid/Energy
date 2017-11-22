package com.ep.energy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ep.energy.R;
import com.ep.energy.db.model.ArtWareData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxiaohui on 2017/11/17.
 */

public class ArtWareAdapter extends EBaseAdapter<ArtWareData> {

    public ArtWareAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.artware_adapter_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.indexTag,viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.indexTag);
        }
        ArtWareData imageUrl = (ArtWareData) getItem(position);
        Glide.with(mContext)
                .load(imageUrl.getArtImgUrl().replace("https","http"))
                .placeholder(R.drawable.energy_default_img)
                .crossFade()
                .animate(AnimationUtils.loadAnimation(mContext, R.anim.load_img_animal))
                .into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.imageView)
        ImageView imageView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
