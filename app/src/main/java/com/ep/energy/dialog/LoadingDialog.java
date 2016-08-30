package com.ep.energy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ep.energy.R;
import com.zxh.q.zlibrary.widget.PathTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/27.
 */
public class LoadingDialog extends Dialog {

    private ViewHolder holder;
    private Context context;

    public LoadingDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.context = context;

        initView(context);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.loadingdialog, null);
        holder = new ViewHolder(rootView);
        setContentView(rootView);
    }

    @Override
    public void show() {
        super.show();
        if(holder != null){
            holder.pathTextView.init(context.getResources().getString(R.string.app_name));
        }
    }

    static class ViewHolder {
        @Bind(R.id.pathTextView)
        PathTextView pathTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
