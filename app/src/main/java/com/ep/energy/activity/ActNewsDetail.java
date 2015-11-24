package com.ep.energy.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ep.energy.R;
import com.ep.energy.fragment.FrgPositiveNews;
import com.zxh.q.zlibrary.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/31.
 */
public class ActNewsDetail extends BaseActivity {
    public static final String PASSVALUE = "pasvalue";

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.source)
    TextView source;
    @Bind(R.id.context)
    TextView context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdeatail);
        ButterKnife.bind(this);

        getvalue();
    }

    private void getvalue() {
        List<FrgPositiveNews.NewsDetailBean> newslist
                = (List<FrgPositiveNews.NewsDetailBean>)getIntent().getSerializableExtra(PASSVALUE);
        String con = "";
        StringBuilder stringBuilder = new StringBuilder();
        FrgPositiveNews.NewsDetailBean newsbean = newslist.get(0);
        for(FrgPositiveNews.NewsDetailBean newsDetailBean : newslist){
            con = stringBuilder.append(newsDetailBean.getContent()).toString();
        }
        title.setText(newsbean.title);
        date.setText(newsbean.pdate_src);
        source.setText(newsbean.src);
        context.setText(con.replace("<em>","").replace("</em>",""));
    }

}
