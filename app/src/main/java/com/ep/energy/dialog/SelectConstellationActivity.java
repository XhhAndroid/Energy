package com.ep.energy.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ep.energy.BaseActivity;
import com.ep.energy.R;
import com.ep.energy.SharePrefrenceUtil;
import com.ep.energy.adapter.EBaseAdapter;
import com.ep.energy.bean.ConstellatBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxiaohui on 2017/5/2.
 */

public class SelectConstellationActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;

    private ConsteAdapter consteAdapter;
    List<ConstellatBean> constellatBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        ButterKnife.bind(this);

        consteAdapter = new ConsteAdapter(new EBaseAdapter.OnclickListner<ConstellatBean>() {
            @Override
            public void onClick0(View v, ConstellatBean constellatBean, int position) {
                SharePrefrenceUtil.setConstellation(constellatBean.getName());
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void OnClick1(View v, ConstellatBean constellatBean, int position) {

            }

            @Override
            public void Onclick2(View v, ConstellatBean constellatBean, int position) {

            }
        }, this);
        constellatBeanList.addAll(getConstellationList());
        consteAdapter.setList(constellatBeanList);
        listView.setAdapter(consteAdapter);

    }

    private List<ConstellatBean> getConstellationList() {
        List<ConstellatBean> constellatBeanList = new ArrayList<>();
        String list[] = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
        String date[] = {"3月21日~4月19日", "4月20日～5月20日", "5月21日～6月21日",
                "6月22日～7月22日", "7月23日～8月22日", "8月23日～9月22日", "9月23日～10月23日", "10月24日～11月22日",
                "11月23日～12月21日", "12月22日～1月19日", "1月20日～2月18日", "2月19日～3月20日",};
        for (int i = 0; i < 12; i++) {
            ConstellatBean constellatBean = new ConstellatBean();
            constellatBean.setName(list[i]);
            constellatBean.setDate(date[i]);
            constellatBeanList.add(constellatBean);
        }
        return constellatBeanList;
    }

    public class ConsteAdapter extends EBaseAdapter<ConstellatBean> {


        public ConsteAdapter(OnclickListner<ConstellatBean> onclickListner, Context mContext) {
            super(onclickListner, mContext);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            super.getView(position, convertView, parent);
            final ConstellatBean constellatBean = (ConstellatBean) getItem(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.constellation_adapter_layout, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.nameTv.setText(constellatBean.getName());
            viewHolder.dateTv.setText(constellatBean.getDate());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickListner.onClick0(v, constellatBean, position);
                }
            });
            return convertView;

        }

        class ViewHolder {
            @Bind(R.id.nameTv)
            TextView nameTv;
            @Bind(R.id.dateTv)
            TextView dateTv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
