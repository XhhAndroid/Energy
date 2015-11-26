package com.zxh.q.zlibrary.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import com.zxh.q.zlibrary.R;

/**
 * Created by jkt-yf-002 on 2015/11/26.
 */
public class ZEditText extends EditText {
    protected Drawable mDrawable;

    public ZEditText(Context context) {
        super(context);
        init();
    }

    public ZEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        Drawable[] drawables = this.getCompoundDrawables();
        //取得right位置的Drawable
        //即我们在布局文件中设置的android:drawableRight
        mDrawable = drawables[2];

        //设置焦点变化的监听
        this.setOnFocusChangeListener(new FocusChangeListenerImpl());
        //设置EditText文字变化的监听
        this.addTextChangedListener(mTextWatcher);
        //初始化时让右边clean图标不可见
        setClearDrawableVisible(false);
    }

    /**
     * 当手指抬起的位置在clean的图标的区域
     * 我们将此视为进行清除操作
     * getWidth():得到控件的宽度
     * event.getX():抬起时的坐标(坐标是相对于控件本身而言的)
     * getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
     * getPaddingRight():clean的图标右边缘至控件右边缘的距离
     * 于是:
     * getWidth() - getTotalPaddingRight()表示:
     * 控件左边到clean的图标左边缘的区域
     * getWidth() - getPaddingRight()表示:
     * 控件左边到clean的图标右边缘的区域
     * 所以这两者之间的区域刚好是clean的图标的区域
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight())) &&
                        (event.getX() < (getWidth() - getPaddingRight()));
                if (isClean) {
                    setText("");
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub
        }
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            boolean isVisible = getText().toString().length() >= 1;
            setClearDrawableVisible(isVisible);
        }
    };
    private class FocusChangeListenerImpl implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                setClearDrawableVisible(false);
            } else {
                boolean isVisible = getText().toString().length() >= 1;
                setClearDrawableVisible(isVisible);
            }
        }
    }
    /**
     * 隐藏或者显示右边clean的图标
     */
    protected void setClearDrawableVisible(boolean isVisible) {
        Drawable rightDrawable;
        if (isVisible) {
            rightDrawable = mDrawable;
        } else {
            rightDrawable = null;
            setShakeAnimation();
        }
        //使用代码设置该控件left, top, right, and bottom处的图标
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);
    }
    /**
     * 显示一个动画,以提示用户输入
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(1));
    }

    //CycleTimes动画重复的次数
    public Animation shakeAnimation(int CycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(300);
        return translateAnimation;
    }
}
