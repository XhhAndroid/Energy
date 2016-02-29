/**
 * 
 */
package com.zxh.q.zlibrary.widget;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * @author Jalen Zheng
 * 
 */
public class FreeRadioGroup implements OnClickListener {
    List<View> radioBtns = new ArrayList<View>();

    View checkedBtn;

    OnCheckedChangeListener onCheckedChangeListener;

    public FreeRadioGroup() {
    }

    public void addRadio(View v) {
        if (v == null) {
            return;
        }
        radioBtns.add(v);
        v.setOnClickListener(this);
    }

    public void addRadio(View parent, int id) {
        View v = parent.findViewById(id);
        addRadio(v);
    }

    public void setChecked(View v) {
        if (v == checkedBtn) {
            return;
        }

        for (View view : radioBtns) {
            if (view == v) {
                if (checkedBtn != null) {
                    checkedBtn.setSelected(false);
                    setChildrenChecked(checkedBtn, false);
                    callOnCheckedChangeListener(checkedBtn);
                }

                view.setSelected(true);
                setChildrenChecked(view, true);
                callOnCheckedChangeListener(view);
                checkedBtn = view;
                break;
            }
        }
    }

    public void setChecked(int id) {
        if (checkedBtn != null && checkedBtn.getId() == id) {
            return;
        }

        for (View view : radioBtns) {
            if (view.getId() == id) {
                setChecked(view);
            }
        }
    }

    private void setChildrenChecked(View parent, boolean isChecked) {
        if (parent == null) {
            return;
        }

        if (parent.getClass().isInstance(ViewGroup.class)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            int childrenNum = viewGroup.getChildCount();
            for (int i = 0; i < childrenNum; i++) {
                View child = viewGroup.getChildAt(i);
                child.setSelected(isChecked);
                setChildrenChecked(child, isChecked);
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public void callOnCheckedChangeListener(View radio) {
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, radio);
        }
    }

    public interface OnCheckedChangeListener {
        public void onCheckedChanged(FreeRadioGroup group, View radio);
    }

    @Override
    public void onClick(View v) {
        setChecked(v);
    }
}
