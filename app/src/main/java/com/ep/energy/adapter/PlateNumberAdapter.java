package com.ep.energy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ep.energy.R;
import com.ep.energy.db.model.PlateNumberInfo;
import com.ep.energy.interfaces.imterfaceImp.BaseAdapterListner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxiaohui on 2017/10/12.
 */

public class PlateNumberAdapter extends EBaseAdapter<PlateNumberInfo> {
    public static int CHAR_NAME = 1;
    public static int CITY_NAME = 2;

    private int contentType = CHAR_NAME;

    public PlateNumberAdapter(Context mContext) {
        super(mContext);
    }

    public PlateNumberAdapter(BaseAdapterListner<PlateNumberInfo> onclickListner, Context mContext) {
        super(onclickListner, mContext);
    }

    public void setContentType(int type) {
        contentType = type;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        final PlateNumberInfo plateNumberInfo = (PlateNumberInfo) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.plate_number_adapter_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (contentType == CHAR_NAME) {
            viewHolder.contentTv.setText(plateNumberInfo.getCity_short_name() + plateNumberInfo.getCity_plate_letter());
            viewHolder.contentTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseAdapterListner.onClick0(v, plateNumberInfo, position);
                }
            });
            baseAdapterListner.OnClick1(viewHolder.contentTv, plateNumberInfo, position);
        } else if (contentType == CITY_NAME) {
            viewHolder.contentTv.setText(plateNumberInfo.getCity_name() + "(" + plateNumberInfo.getCity_remark() + ")");
        }
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.contentTv)
        TextView contentTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
