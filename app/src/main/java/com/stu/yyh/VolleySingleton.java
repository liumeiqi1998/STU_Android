package com.stu.yyh;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2019/3/28.
 */


public class VolleySingleton {//单列模式
    public static VolleySingleton volleySingleton;
    public RequestQueue mRequestQueue;// 请求队列
    Context context;
    ImageLoader mImageLoader;

    public VolleySingleton(Context context)
    {
        this.context = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url)
                    {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap)
                    {
                        cache.put(url, bitmap);
                    }
                });
    }

    /**
     * 获取请求队列
     * @return
     */
    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized VolleySingleton getVolleySingleton(
            Context context)
    {
        if (volleySingleton == null)
        {
            volleySingleton = new VolleySingleton(context.getApplicationContext());
        }
        return volleySingleton;
    }

    //添加请求任务到请求队列中
    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader()
    {
        return mImageLoader;
    }

}
