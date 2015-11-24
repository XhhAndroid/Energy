package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ep.energy.R;
import com.zxh.q.zlibrary.BaseFragment;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.widget.ZTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgUserCenter extends BaseFragment {
    @Bind(R.id.reputation)
    TextView reputation;
    @Bind(R.id.ztextview)
    ZTextView ztextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_usercenter, container, false);
        ButterKnife.bind(this, layout);

        initview();

        return layout;
    }

    private void initview() {
        List<String> textlist = new ArrayList<>();
        textlist.add("名字");
        textlist.add("性别");
        textlist.add("数字");

        List<Integer> textcolorlist = new ArrayList<>();
        textcolorlist.add(R.color.theme_color);
        textcolorlist.add(R.color.holo_blue);
        textcolorlist.add(R.color.theme_color);

        List<Integer> textsizelist = new ArrayList<>();
        textsizelist.add(14);
        textsizelist.add(17);
        textsizelist.add(12);

        ztextview.setdata(textsizelist, textlist,textcolorlist);

        reputation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastZ.Toastz(getActivity(), "我的信誉");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
