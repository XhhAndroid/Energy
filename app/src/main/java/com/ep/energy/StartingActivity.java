package com.ep.energy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 启动页
 * Created by zhangxiaohui on 2017/9/18.
 */

public class StartingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartingActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}
