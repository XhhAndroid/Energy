package com.zxh.q.zlibrary.view;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxh.q.zlibrary.bean.ViewBean;
import com.zxh.q.zlibrary.view.interfa.PageChange;

import java.util.List;


/**
 * Created by Administrator on 2015/10/11.
 */
public class ViewAdapter extends PagerAdapter {
    private Activity context;
    private PageSwitchViewpager mJazzy;
    private List<ViewBean> viewList;
    LayoutInflater inflater = null;
    PageChange pagechange;

    public ViewAdapter(Activity context, PageSwitchViewpager mJazzy, List<ViewBean> viewList) {
        this.viewList = viewList;
        this.context = context;
        this.mJazzy = mJazzy;
        inflater = context.getLayoutInflater();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewBean vb = viewList.get(position);

        View view = inflater.inflate(vb.getViewroot(), container,false);
        container.addView(view);
        mJazzy.setObjectForPosition(view, position);

        return view;
    }

    public PageChange getPagechange() {
        return pagechange;
    }

    public void setPagechange(PageChange pagechange) {
        this.pagechange = pagechange;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        pagechange.pageswitch(position, viewList.size());

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object obj) {
        container.removeView(mJazzy.findViewFromObject(position));
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        if (view instanceof OutlineContainer) {
            return ((OutlineContainer) view).getChildAt(0) == obj;
        } else {
            return view == obj;
        }
    }
}
