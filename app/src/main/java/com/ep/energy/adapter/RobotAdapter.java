package com.ep.energy.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ep.energy.R;
import com.ep.energy.bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/31.
 */
public class RobotAdapter extends BaseAdapter {
    private Activity context;
    private List<NewsBean> newsBeanList;
    private LayoutInflater inflater;
    private ListView listView;

    public RobotAdapter(Activity activity, List<NewsBean> newslist,ListView listView) {
        this.context = activity;
        this.newsBeanList = newslist;
        this.listView = listView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newsBeanList.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder = new Holder();
        NewsBean newsbean = newsBeanList.get(position);
        if(view == null){
            view = inflater.inflate(R.layout.robot_item,null);
        }
        holder.tvword = (TextView)view.findViewById(R.id.textview);

        holder.tvword.setText(newsbean.text.replace("图灵","正能量"));
        holder.tvword.setGravity(Gravity.RIGHT);
        if("left".equals(newsbean.gravity)){
            holder.tvword.setGravity(Gravity.LEFT);
        }
        listView.setSelection(position);
        return view;
    }
    class Holder{
        TextView tvword;
    }
}
