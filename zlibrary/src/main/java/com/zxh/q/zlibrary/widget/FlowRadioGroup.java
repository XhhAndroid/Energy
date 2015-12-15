package com.zxh.q.zlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FlowRadioGroup extends LinearLayout {
    private int mCheckedId = -1;

    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;

    private boolean mProtectFromCheckedChange = false;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    private PassThroughHierarchyChangeListener mPassThroughListener;

    private boolean isClearCheckedWhenDoubleClick = true;

    public FlowRadioGroup(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCheckedId = View.NO_ID;

        final int index = VERTICAL;
        setOrientation(index);

        init();
    }

    public boolean isClearCheckedWhenDoubleClick() {
        return isClearCheckedWhenDoubleClick;
    }

    public void setIsClearCheckedWhenDoubleClick(boolean isClearCheckedWhenDoubleClick) {
        this.isClearCheckedWhenDoubleClick = isClearCheckedWhenDoubleClick;
    }

    private void init() {
        mChildOnCheckedChangeListener = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (mCheckedId != -1) {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof RadioButton) {

            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int checkedId = child.getId();
                    if (checkedId != -1 && checkedId == mCheckedId && isClearCheckedWhenDoubleClick) {
                        clearCheck();

                        if (mOnCheckedChangeListener != null) {
                            mOnCheckedChangeListener.onClearChecked(FlowRadioGroup.this, checkedId);
                        }
                    } else {
                        ((RadioButton) child).setChecked(true);
                        checkRadioButton((RadioButton) child);
                        if (mOnCheckedChangeListener != null) {
                            mOnCheckedChangeListener.onCheckedChanged(FlowRadioGroup.this, checkedId);
                            setCheckedId(child.getId());
                        }
                    }
                }
            });
        } else if (child instanceof LinearLayout) {
            int childCount = ((LinearLayout) child).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = ((LinearLayout) child).getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton) view;

                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int checkedId = button.getId();
                            if (checkedId != -1 && checkedId == mCheckedId && isClearCheckedWhenDoubleClick) {
                                clearCheck();

                                if (mOnCheckedChangeListener != null) {
                                    mOnCheckedChangeListener.onClearChecked(FlowRadioGroup.this, checkedId);
                                }
                            } else {
                                (button).setChecked(true);
                                checkRadioButton(button);
                                if (mOnCheckedChangeListener != null) {
                                    mOnCheckedChangeListener.onCheckedChanged(FlowRadioGroup.this, checkedId);
                                    setCheckedId(button.getId());
                                }
                            }
                        }
                    });
                }
            }
        }

        super.addView(child, index, params);
    }

    private void checkRadioButton(RadioButton radioButton) {
        View child;
        int radioCount = getChildCount();
        for (int i = 0; i < radioCount; i++) {
            child = getChildAt(i);
            if (child instanceof RadioButton) {
                if (child == radioButton) {
                    // do nothing
                } else {
                    ((RadioButton) child).setChecked(false);
                }
            } else if (child instanceof LinearLayout) {
                int childCount = ((LinearLayout) child).getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View view = ((LinearLayout) child).getChildAt(j);
                    if (view instanceof RadioButton) {
                        final RadioButton button = (RadioButton) view;
                        if (button == radioButton) {
                            // do nothing
                        } else {
                            ((RadioButton) button).setChecked(false);
                        }
                    }
                }
            }
        }
    }

    public void check(int id) {
        if (-1 == id && mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }

        // don't even bother
        if (id != -1 && (id == mCheckedId)) {
            return;
        }

        if (mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }

        if (id != -1) {
            setCheckedStateForView(id, true);
        }

        setCheckedId(id);
    }

    private void setCheckedId(int id) {
        mCheckedId = id;
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof RadioButton) {
            ((RadioButton) checkedView).setChecked(checked);
        }
    }

    public int getCheckedRadioButtonId() {
        return mCheckedId;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof RadioGroup.LayoutParams;
    }

    @Override
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        event.setClassName(FlowRadioGroup.class.getName());
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(int w, int h, float initWeight) {
            super(w, h, initWeight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {

            if (a.hasValue(widthAttr)) {
                width = a.getLayoutDimension(widthAttr, "layout_width");
            } else {
                width = WRAP_CONTENT;
            }

            if (a.hasValue(heightAttr)) {
                height = a.getLayoutDimension(heightAttr, "layout_height");
            } else {
                height = WRAP_CONTENT;
            }
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(FlowRadioGroup group, int checkedId);

        void onClearChecked(FlowRadioGroup group, int checkedId);
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mProtectFromCheckedChange) {
                return;
            }

            mProtectFromCheckedChange = true;
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;

            int id = buttonView.getId();
            setCheckedId(id);
        }
    }

    private class PassThroughHierarchyChangeListener implements OnHierarchyChangeListener {
        private OnHierarchyChangeListener mOnHierarchyChangeListener;

        public void onChildViewAdded(View parent, View child) {
            if (parent == FlowRadioGroup.this && child instanceof RadioButton) {
                int id = child.getId();
                if (id == View.NO_ID) {
                    id = child.hashCode();
                    child.setId(id);
                }
                ((RadioButton) child).setOnCheckedChangeListener(mChildOnCheckedChangeListener);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            if (parent == FlowRadioGroup.this && child instanceof RadioButton) {
                ((RadioButton) child).setOnCheckedChangeListener(null);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}