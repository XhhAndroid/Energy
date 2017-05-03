package com.ep.energy.activity;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangxiaohui on 2017/5/3.
 */

public class OOMLogActivity extends BaseActivity {
    @Bind(R.id.listview)
    ListView listview;

    LogAdapter logAdapter;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oom_log_activity_layout);
        ButterKnife.bind(this);

        logAdapter = new LogAdapter(this);
        Set<String> logList = SharePrefrenceUtil.getOOMLog();
        for (String text : logList) {
            stringList.add(text);
        }
        logAdapter.setList(stringList);
        listview.setAdapter(logAdapter);
    }

    @OnClick(R.id.clearAll)
    public void onClick() {
        stringList.clear();
        SharePrefrenceUtil.clearOOMLog();
        logAdapter.notifyDataSetChanged();
    }

    class LogAdapter extends EBaseAdapter<String> {

        public LogAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            super.getView(position, convertView, parent);
            String logText = (String) getItem(position);
            convertView = inflater.inflate(R.layout.oom_log_adapter_layout, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.logTv);
            textView.setText(logText);
            return convertView;
        }
    }
}
