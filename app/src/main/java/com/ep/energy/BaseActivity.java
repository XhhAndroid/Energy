package com.ep.energy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.ep.energy.utils.SystemStatusManager;


public class BaseActivity extends AppCompatActivity {
    long firstClickTime = 0;
    private boolean doubleClick = false;

    private int topbarlayout = com.zxh.q.zlibrary.R.layout.baseactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        // 需要setContentView之前调用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemStatusManager tintManager = new SystemStatusManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // 设置状态栏的颜色
            tintManager.setStatusBarTintResource(R.color.theme_color);
            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }

    /**
     * 开启双击关闭
     */
    public void doubleClickFinish() {
        doubleClick = true;
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: closeKeyBoard
     * @Description: TODO(关闭软键盘)
     */
    public void closeKeyBoard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstClickTime = 0;
        doubleClick = false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (System.currentTimeMillis() - firstClickTime < 190) {
                    if (doubleClick) {
                        finish();
                    }
                    firstClickTime = 0;
                }
                firstClickTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * @param view 点击或touch某一个view组件打开软键盘
     * @return void 返回类型
     * @throws
     * @Title: openKeyBoard
     * @Description: TODO(打开软键盘)
     */
    public void openKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }
}
