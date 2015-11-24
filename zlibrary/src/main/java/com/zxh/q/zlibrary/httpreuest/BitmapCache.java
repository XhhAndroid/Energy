package com.zxh.q.zlibrary.httpreuest;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.zxh.q.zlibrary.volley.toolbox.ImageLoader.ImageCache;

/**
 * Created by Administrator on 2015/11/24.
 */
public class BitmapCache implements ImageCache {
    private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
