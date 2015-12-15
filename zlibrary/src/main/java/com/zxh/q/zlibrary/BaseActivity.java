package com.zxh.q.zlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class BaseActivity extends AppCompatActivity {
    private int topbarlayout = R.layout.baseactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
