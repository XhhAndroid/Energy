package com.ep.energy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ep.energy.dialog.LoadingDialog;


public class BaseActivity extends AppCompatActivity {
    long firstClickTime = 0;
    private boolean doubleClick = false;
    public LoadingDialog loadingDialog;
    private Toast toast;

    private int topbarlayout = com.zxh.q.zlibrary.R.layout.baseactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
    }

    public void showToast(String showText) {
        if(toast == null){
            toast = new Toast(this);
            toast.setText(showText);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }else {
            toast.cancel();
            toast.setText(showText);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.show();
    }

    public void dissmissLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.dismiss();
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
