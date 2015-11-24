package com.zxh.q.zlibrary.httpreuest.interfa;

import com.zxh.q.zlibrary.volley.VolleyError;

/**
 * Created by jkt-yf-002 on 2015/10/29.
 */
public interface Httpinterface {

    public void Success(String response);

    public void Error(VolleyError response);

}
