package com.stu.lwj.util;


import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Roger on 2018/10/27.
 */
public class OkhttpUtil {
    private static Callback DeafultCallback = null;
    public static Response syncGet(String URL) throws IOException {
        // 同步get方法，会堵塞线程
        Request request = new Request.Builder()
                .get()
                .url(URL)
                .build();
        return new OkHttpClient().newCall(request).execute();
    }
    public static void aysnGet(String URL, Callback callback) {
        Request request = new Request.Builder()
                .get()
                .url(URL)
                .build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }
    public static void getMap(String URL, Map<String,String> map, Callback callback) {
        String mapStr = FormatUtil.mapToUrlParam(map);
        Request request = new Request.Builder()
                .get()
                .url(URL+mapStr)
                .build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    public static void postMultipartBody(String URL, MultipartBody multipartBody, Callback callback) {
        Request request = new Request.Builder()
            .url(URL)
            .post(multipartBody)
            .build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    public static void postRequestBody(String URL, RequestBody requestBody, Callback callback) {
        Request request = new Request.Builder()
            .url(URL)
            .post(requestBody)
            .build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    public static void postJson(String URL, String json, Callback callback){
        final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonType, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    public static Callback getDeafultCallback(){
        if (DeafultCallback == null) {
            DeafultCallback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Roger::Failure", e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("Roger::Response", response.body().string());
                }
            };
        }
        return DeafultCallback;
    }
}




