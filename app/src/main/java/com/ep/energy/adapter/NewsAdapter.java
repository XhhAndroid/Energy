package com.ep.energy.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ep.energy.R;
import com.ep.energy.bean.NewsBean;

import java.util.List;

/**
 * Created by jkt-yf-002 on 2015/10/29.
 */
public class NewsAdapter extends BaseAdapter{
    private Activity context;
    private List<NewsBean> newsBeanList;
    private LayoutInflater inflater;

    public NewsAdapter(Activity activity, List<NewsBean> newslist) {
        this.context = activity;
        this.newsBeanList = newslist;
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
            view = inflater.inflate(R.layout.newslist_item,null);
        }
        holder.title = (TextView)view.findViewById(R.id.title);
        holder.time = (TextView)view.findViewById(R.id.time);
        holder.source = (TextView)view.findViewById(R.id.source);
        holder.title.setText(newsbean.text);
        holder.time.setText(newsbean.time);
        holder.source.setText(newsbean.source);

        return view;
    }
    class Holder{
        TextView title;
        TextView time;
        TextView source;
    }
}
