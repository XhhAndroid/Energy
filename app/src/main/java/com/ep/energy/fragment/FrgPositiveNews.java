package com.ep.energy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ep.energy.R;
import com.ep.energy.activity.ActNewsDetail;
import com.ep.energy.adapter.NewsAdapter;
import com.ep.energy.bean.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxh.q.zlibrary.BaseFragment;
import com.zxh.q.zlibrary.httpreuest.Http;
import com.zxh.q.zlibrary.httpreuest.interfa.Httpinterface;
import com.zxh.q.zlibrary.utils.DataTimeZ;
import com.zxh.q.zlibrary.utils.StringZ;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.volley.VolleyError;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgPositiveNews extends BaseFragment {
    @Bind(R.id.input)
    EditText input;
    @Bind(R.id.btnsearch)
    ImageView btnsearch;
    @Bind(R.id.lin)
    LinearLayout lin;
    @Bind(R.id.listview)
    ListView listview;

    private NewsAdapter adapter;
    private List<NewsBean> newslist = new ArrayList<>();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    enum NEWSTYPE {SEARCH, NORMAL}

    private NEWSTYPE newstype = NEWSTYPE.NORMAL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_positivenews, container, false);
        ButterKnife.bind(this, layout);

        initview();
        initdata("");

        return layout;
    }

    /**
     * 请求新闻数据
     *
     * @param Keyword
     */
    private void initdata(String Keyword) {

        Map<String, String> params = new HashMap<>();
        params.put("key", "c6ba38895c72e6d57bbb5a05ef53744f");

        String url;
        if (StringZ.Isempty(Keyword)) {
            newstype = NEWSTYPE.NORMAL;
            url = "http://op.juhe.cn/onebox/news/words";
        } else {
            newstype = NEWSTYPE.SEARCH;
            params.put("q", Keyword.trim());
            url = "http://op.juhe.cn/onebox/news/query";
        }
        Http http = new Http(getActivity());
        http.Z(url, params, new Httpinterface() {
            @Override
            public void Success(String response) {
                if (!StringZ.Isempty(response)) {
                    Gson gson = new Gson();
                    switch (newstype) {
                        case SEARCH:
                            SearchNews(gson, response);
                            break;
                        case NORMAL:
                            NormalNews(gson, response);
                            break;
                    }
                }
            }

            @Override
            public void Error(VolleyError response) {
                ToastZ.Toastz(getActivity(), response.getMessage());
            }
        });
    }

    /**
     * 实时热点新闻
     *
     * @param gson
     * @param response
     */
    private void NormalNews(Gson gson, String response) {
        Type type = new TypeToken<Result>() {
        }.getType();
        Result result = gson.fromJson(response, type);
        if (result != null) {
            if (result.error_code == 0) {

                List<String> datalist = result.result;
                if (datalist != null && datalist.size() > 0) {
                    for (String word : datalist) {
                        NewsBean news = new NewsBean();
                        news.text = word;
                        news.time = DataTimeZ.getCurrentTime();

                        newslist.add(news);
                    }
                }

                adapter.notifyDataSetChanged();
            } else {
                ToastZ.Toastz(getActivity(), result.reason);
            }
        }
    }

    /**
     * 新闻搜索
     *
     * @param gson
     * @param response
     */
    private void SearchNews(Gson gson, String response) {
        Type type = new TypeToken<Result1>() {
        }.getType();
        Result1 result = gson.fromJson(response, type);
        if (result != null) {
            if (result.error_code == 0) {
                List<NewsDetailBean> newslist = result.result;
                if (newslist != null && newslist.size() > 0) {

                    Intent intent = new Intent(getActivity(), ActNewsDetail.class);
                    intent.putExtra(ActNewsDetail.PASSVALUE, (Serializable) newslist);
                    startActivity(intent);
                }
            } else {
                ToastZ.Toastz(getActivity(), result.reason);
            }

        }

    }

    private void initview() {
        lin.setVisibility(View.GONE);
        adapter = new NewsAdapter(getActivity(), newslist);
        listview.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringZ.Isempty(input)) {
                    initdata(input.getText().toString());
                }
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                initdata(newslist.get(i).getText());
            }
        });
    }

    /**
     * bean相关
     */
    class Result {
        int error_code;
        String reason;
        List<String> result;

        public List<String> getResult() {
            return result;
        }

        public void setResult(List<String> result) {
            this.result = result;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    class Result1 {
        int error_code;
        String reason;
        List<NewsDetailBean> result;

        public List<NewsDetailBean> getResult() {
            return result;
        }

        public void setResult(List<NewsDetailBean> result) {
            this.result = result;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public class NewsDetailBean implements Serializable {
        public String title;
        public String content;
        public String src;
        public String pdate_src;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }
    }
}
