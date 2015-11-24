package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ep.energy.R;
import com.ep.energy.adapter.RobotAdapter;
import com.ep.energy.bean.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxh.q.zlibrary.BaseFragment;
import com.zxh.q.zlibrary.httpreuest.Http;
import com.zxh.q.zlibrary.httpreuest.interfa.Httpinterface;
import com.zxh.q.zlibrary.utils.StringZ;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.volley.VolleyError;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/31.
 */
public class FrgRobot extends BaseFragment {
    @Bind(R.id.input)
    EditText input;
    @Bind(R.id.btnsend)
    ImageView send;
    @Bind(R.id.lin)
    LinearLayout lin;
    @Bind(R.id.listview)
    ListView listview;

    private RobotAdapter adapter;
    private List<NewsBean> newslist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frgrobot, container, false);

        ButterKnife.bind(this, layout);

        adapter = new RobotAdapter(getActivity(), newslist, listview);
        listview.setAdapter(adapter);

        initdata("你好");

        return layout;
    }

    private void initdata(String word) {

        Map<String, String> params = new HashMap<>();
        params.put("key", "d374b945df988b1dd0813723f96c322b");
        params.put("info", word);

        final Type type = new TypeToken<Result>() {
        }.getType();
        String url = "http://op.juhe.cn/robot/index";
        Http http = new Http(getActivity());
        http.Z(url, params, new Httpinterface() {
            String msg = "";

            @Override
            public void Success(String response) {
                if (!StringZ.Isempty(response)) {
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response, type);
                    if (result != null) {
                        msg = result.reason;
                        if (result.error_code == 0) {
                            Data data = result.result;
                            if (data != null) {
                                NewsBean news = new NewsBean();
                                news.text = data.text;
                                news.gravity = "left";

                                newslist.add(news);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastZ.Toastz(getActivity(), msg);
                        }
                    }
                }
            }

            @Override
            public void Error(VolleyError response) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringZ.Isempty(input.getText() + "")) return;

                NewsBean news = new NewsBean();
                news.text = input.getText().toString();
                news.gravity = "right";
                newslist.add(news);
                adapter.notifyDataSetChanged();

                initdata(input.getText().toString());

                input.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class Result {
        int error_code;
        String reason;
        Data result;

        public Data getResult() {
            return result;
        }

        public void setResult(Data result) {
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

    class Data {
        String code;
        String text;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
