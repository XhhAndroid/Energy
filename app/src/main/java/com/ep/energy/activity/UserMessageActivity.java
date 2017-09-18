package com.ep.energy.activity;

import android.os.Bundle;

import com.ep.energy.BaseActivity;
import com.ep.energy.R;
import com.ep.energy.layoutView.ETopBar;
import com.ep.energy.viewInterface.TopBarOnclick;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 消息列表
 * Created by zhangxiaohui on 2017/9/18.
 */

public class UserMessageActivity extends BaseActivity {
    @Bind(R.id.topBar)
    ETopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_message_layout);
        ButterKnife.bind(this);

        topBar.setTopBarOnclick(new TopBarOnclick() {
            @Override
            public void LeftOnclick() {
                super.LeftOnclick();
                finish();
            }
        });
    }
}
