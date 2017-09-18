package com.ep.energy.layoutView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ep.energy.R;
import com.ep.energy.viewInterface.TopBarOnclick;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangxiaohui on 2017/9/18.
 */

public class ETopBar extends LinearLayout {
    private ViewHolder viewHolder;
    private TopBarOnclick topBarOnclick;
    private boolean showLeftMenu = false;
    private boolean showRightMenu = false;

    public void setTopBarOnclick(TopBarOnclick topBarOnclick) {
        this.topBarOnclick = topBarOnclick;
    }

    public ETopBar(Context context) {
        this(context, null);
    }

    public ETopBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ETopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.topbar);
        showLeftMenu = ta.getBoolean(R.styleable.topbar_showLeftMenu, false);
        showRightMenu = ta.getBoolean(R.styleable.topbar_showRightMenu, false);
        String title = ta.getString(R.styleable.topbar_showTitle);
        ta.recycle();
        initView(context);
        viewHolder.middle.setText(title);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.toolbar, this, false);
        viewHolder = new ViewHolder(rootView);
        viewHolder.leftRel.setVisibility(showLeftMenu ? VISIBLE : INVISIBLE);
        viewHolder.rightRel.setVisibility(showRightMenu ? VISIBLE : INVISIBLE);
        addView(rootView);
        onclickListner();
    }

    private void onclickListner() {
        viewHolder.leftRel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topBarOnclick != null) {
                    topBarOnclick.LeftOnclick();
                }
            }
        });
        viewHolder.middleRel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topBarOnclick != null) {
                    topBarOnclick.TitleOnclick();
                }
            }
        });
        viewHolder.rightRel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topBarOnclick != null) {
                    topBarOnclick.RightOnclick();
                }
            }
        });
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            viewHolder.middle.setText("");
            return;
        }
        viewHolder.middle.setText(title);
    }

    class ViewHolder {
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
        Toolbar mToolbar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
