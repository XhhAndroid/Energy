package com.zxh.q.zlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zxh.q.zlibrary.R;

/**
 * Created by jkt-yf-002 on 2015/9/14.
 * @author zhangxh
 */
public class LoadingDialog extends Dialog{
    private Context context;
    private static LoadingDialog loaddialog;
    private TextView title;
    private TextView message;
    private TextView leftbtn;
    private TextView rightbtn;
    private OnClickListener leftonclick;
    private OnClickListener rightonclick;

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;

        View view = View.inflate(context,R.layout.loaddialog,null);
        setContentView(view);

        initview(view);
    }

    private void initview(View view) {
        title = (TextView) view.findViewById(R.id.titletext);
        message = (TextView) view.findViewById(R.id.message);
        leftbtn = (TextView) view.findViewById(R.id.submit);
        rightbtn = (TextView) view.findViewById(R.id.cancel);
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(leftonclick != null)
                    leftonclick.onClick(loaddialog,0);
            }
        });
        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(rightonclick != null)
                    rightonclick.onClick(loaddialog,0);
            }
        });
    }
    public static LoadingDialog getDialog(Context context) {
        loaddialog = new LoadingDialog(context);
        return loaddialog;
    }

    public LoadingDialog setLeftonclick(OnClickListener leftonclick) {
        this.leftonclick = leftonclick;
        return loaddialog;
    }

    public LoadingDialog setRightonclick(OnClickListener rightonclick) {
        this.rightonclick = rightonclick;
        return loaddialog;
    }

    /**
     * @Description 设置标题文字
     * @param text
     * @return
     */
    public LoadingDialog settitle(String text){
        title.setText(text);
        return loaddialog;
    }

    /**
     * @Description 设置内容文字
     * @param msg
     * @return
     */
    public LoadingDialog setmessage(String msg){
        message.setText(msg);
        return loaddialog;
    }

    /**
     * @Description 设置左边按钮文字
     * @param t
     * @return
     */
    public LoadingDialog setleftbtntext(String t){
        leftbtn.setText(t);
        return loaddialog;
    }

    /**
     * @Description 设置右边按钮文字
     * @param t
     * @return
     */
    public LoadingDialog setrightbtntext(String t){
        rightbtn.setText(t);
        return loaddialog;
    }


}
