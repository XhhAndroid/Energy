package com.ep.energy;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxh.q.zlibrary.BaseActivity;
import com.zxh.q.zlibrary.bean.ViewBean;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.view.PageSwitchViewpager;
import com.zxh.q.zlibrary.view.ViewAdapter;
import com.zxh.q.zlibrary.view.interfa.PageChange;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements PageChange {
    @Bind(R.id.left)
    ImageView left;
    @Bind(R.id.left_rel)
    RelativeLayout leftRel;
    @Bind(R.id.middle)
    TextView middle;
    @Bind(R.id.middle_rel)
    RelativeLayout middleRel;
    @Bind(R.id.right)
    ImageView right;
    @Bind(R.id.right_rel)
    RelativeLayout rightRel;
    @Bind(R.id.m_toolbar)
    Toolbar myToolbar;
    @Bind(R.id.jazzy_pager)
    PageSwitchViewpager mJazzy;

    private List<ViewBean> viewlist = new ArrayList<>();

    private int viewl[] = {R.layout.positiveenergy, R.layout.positivemap,
            R.layout.positivenews, R.layout.robot, R.layout.usercenter};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayShowTitleEnabled(false);

        setupJazziness(PageSwitchViewpager.TransitionEffect.Tablet);

        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.left_rel:
                        ToastZ.Toastz(MainActivity.this, "向左");
                        break;
                }
                return true;
            }
        });
    }

    private void setupJazziness(PageSwitchViewpager.TransitionEffect effect) {
        mJazzy.setTransitionEffect(effect);
        ViewAdapter viewAdapter = new ViewAdapter(MainActivity.this, mJazzy, ViewList());
        viewAdapter.setPagechange(this);
        mJazzy.setAdapter(viewAdapter);
        mJazzy.setPageMargin(1);
        mJazzy.setOutlineEnabled(true);//添加外边框
    }

    private List<ViewBean> ViewList() {
        for (int aViewl : viewl) {
            ViewBean viewBean = new ViewBean();
            viewBean.setViewroot(aViewl);
            viewlist.add(viewBean);
        }
        return viewlist;
    }

    @Override
    public void pageswitch(int position, int size) {
        switch (position % size) {
            case 0:
                middle.setText("Ep");
                show(1);
                break;
            case 1:
                middle.setText("Ep分布");
                show(2);
                break;
            case 2:
                middle.setText("Ep新闻");
                show(2);
                break;
            case 3:
                middle.setText("Ep机器人");
                show(2);
                break;
            case 4:
                middle.setText("Ep个人中心");
                show(3);
                break;
        }
    }

    private void show(int i) {
        switch (i) {
            case 1:
                leftRel.setVisibility(View.INVISIBLE);
                rightRel.setVisibility(View.VISIBLE);
                break;
            case 2:
                leftRel.setVisibility(View.VISIBLE);
                rightRel.setVisibility(View.VISIBLE);
                break;
            case 3:
                leftRel.setVisibility(View.VISIBLE);
                rightRel.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
