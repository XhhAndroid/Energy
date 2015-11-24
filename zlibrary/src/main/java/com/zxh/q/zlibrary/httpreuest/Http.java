package com.zxh.q.zlibrary.httpreuest;

import android.content.Context;

import com.zxh.q.zlibrary.httpreuest.interfa.Httpinterface;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.volley.AuthFailureError;
import com.zxh.q.zlibrary.volley.Request;
import com.zxh.q.zlibrary.volley.RequestQueue;
import com.zxh.q.zlibrary.volley.Response;
import com.zxh.q.zlibrary.volley.VolleyError;
import com.zxh.q.zlibrary.volley.toolbox.StringRequest;
import com.zxh.q.zlibrary.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by jkt-yf-002 on 2015/9/11.
 */
public class Http {
    private Context context;
    RequestQueue requestQueue = null;

    public Http(Context context){
        this.context = context;
    }



    public void Z(String requrl,final Map<String, String> param,final Httpinterface http){

        String url = requrl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                http.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastZ.Toastz(context,error.getMessage());
                http.Error(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 提交参数
                Map<String, String> params = param;
                return params;
            }
        };
        if(requestQueue == null) requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
