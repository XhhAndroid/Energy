package com.zxh.q.zlibrary.httpreuest;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.zxh.q.zlibrary.R;
import com.zxh.q.zlibrary.httpreuest.interfa.Httpinterface;
import com.zxh.q.zlibrary.utils.ToastZ;
import com.zxh.q.zlibrary.volley.AuthFailureError;
import com.zxh.q.zlibrary.volley.Request;
import com.zxh.q.zlibrary.volley.RequestQueue;
import com.zxh.q.zlibrary.volley.Response;
import com.zxh.q.zlibrary.volley.VolleyError;
import com.zxh.q.zlibrary.volley.toolbox.ImageLoader;
import com.zxh.q.zlibrary.volley.toolbox.ImageRequest;
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

    public void http(String requrl, final Map<String, String> param, final Httpinterface http){

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
        inithttp();
        requestQueue.add(stringRequest);
    }

    /**
     * 图片缓存的方式加载图片
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public void imageLoader(String url,ImageView imageView,int width,int height){
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        if(width != 0 && height != 0){
            imageLoader.get(url, listener,width,height);
        }else{
            imageLoader.get(url, listener);
        }

    }
    ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
    /**
     * 三、四个参数表示允许图片最大的宽度和高度、为0的话就表示不管图片有多大都不会进行压缩
     * @param imageurl
     * @param imageView
     * @param default_image
     */
    public void imageUrl(final String imageurl,final ImageView imageView,final int default_image){
        ImageRequest imageRequest = new ImageRequest(imageurl,new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                imageView.setImageResource(default_image == 0?R.drawable.default_image:default_image);
            }
        });
        inithttp();
        requestQueue.add(imageRequest);
    }

    /**
     * 网络初始化
     */
    private void inithttp(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }
}
