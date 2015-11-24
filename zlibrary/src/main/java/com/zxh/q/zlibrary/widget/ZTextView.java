package com.zxh.q.zlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zxh.q.zlibrary.R;
import com.zxh.q.zlibrary.utils.LogZ;
import com.zxh.q.zlibrary.utils.Zscreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkt-yf-002 on 2015/11/23.
 */
public class ZTextView extends TextView {
    private Context context;
    private int mTextSize = 0;
    private int mTextColor = android.R.color.black;
    private String mText = "";
    private float paddingleft = 0;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    private List<TextBean> paiintlist = new ArrayList<>();

    public ZTextView(Context context) {
        this(context, null);
    }

    public ZTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mTextSize = textsize(14);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.mTextViewStyle, defStyleAttr, 0);
        paddingleft = array.getDimension(R.styleable.mTextViewStyle_mPaddingleft, 36);
        paddingleft = Zscreen.px2dip(context, paddingleft);
        array.recycle();
    }

    /**
     * 转换字体大小
     * @param a
     * @return
     */
    private int textsize(int a){
        return Zscreen.dip2px(context, a);
    }
    /**
     * 设置文字数据
     * @param sizeList
     * @param textList
     * @param colorList
     */
    public void setdata(List<Integer> sizeList, List<String> textList, List<Integer> colorList) {
        if (textList.size() < 1) {
            LogZ.e("nothing output");
        } else {
            mPaint = new Paint();
            for (int i = 0; i < textList.size(); i++) {
                TextBean tb = new TextBean();
                mText = textList.get(i);

                if (sizeList.size() < 1) {
                    mPaint.setTextSize(mTextSize);
                } else {
                    mPaint.setTextSize(textsize(sizeList.get(i)));
                }

                if (colorList.size() < 1) {
                    mPaint.setColor(context.getResources().getColor(mTextColor));
                } else {
                    mPaint.setColor(context.getResources().getColor(colorList.get(i)));
                }

                mBound = new Rect();
                mPaint.getTextBounds(mText, 0, mText.length(), mBound);
                tb.paint = mPaint;
                tb.text = mText;
                tb.color = mPaint.getColor();
                tb.rect = mBound;

                paiintlist.add(tb);
            }
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), p);
        int i = 0;
        for (TextBean textBean : paiintlist) {
            textBean.paint.setColor(textBean.color);
            canvas.drawText(textBean.text,
                    paddingleft + textBean.rect.width() * i,getHeight() / 2 + textBean.rect.height() / 2,
                    textBean.paint);

            i++;
        }
    }

    class TextBean {
        Paint paint;
        Rect rect;
        String text;
        int color;

        public Rect getRect() {
            return rect;
        }

        public void setRect(Rect rect) {
            this.rect = rect;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
