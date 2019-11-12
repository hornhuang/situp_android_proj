package com.fishinwater.situp.util;

import android.content.Context;
import android.util.Log;

import com.fishinwater.situp.callback.MyBitmapCallback;
import com.fishinwater.situp.callback.MyFileCallBack;
import com.fishinwater.situp.callback.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求封装
 * @author fiinwater-1999
 */
public class OkHttpUtil {

    private static final String TAG = "OkHttpUtil";

    // ----------------------------- POST -----------------------------

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * okHttp post请求
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String uploadStringByPOST(String url, String json, OkHttpClient client) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 使用 ok_http_Utils 获得请求数据
     */
    public static void getDataByPOST(String url, MyStringCallback callback)
    {
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(callback);
    }

    // ----------------------------- GET -----------------------------

    /**
     * get请求
     * @param url 网络连接
     * @return
     * @throws IOException
     */
    public static String getStringByGET(String url, OkHttpClient client) throws IOException {//OkHttp方法不能再主线程中执行/*所以要间隔子线程，并在其中执行*/
        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.e(TAG,"333------------------333------"+request);
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 使用 ok_http_Utils 获得请求数据
     */
    public static void getDataByGET(String url, MyStringCallback callback)
    {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * 使用 okHttp_Utils 下载大文件
     */
    public static void getHugefileByGET(String url, MyFileCallBack fileCallBack)
    {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(fileCallBack);
    }

    /**
     * 使用 okHttp 获取图片
     * @param
     */
    public static void getImageByGET(String url, Context context, MyBitmapCallback bitmapCallback)
    {
        OkHttpUtils
                .get()
                .url(url)
                .tag(context)
                .build()
                //连接超时
                .connTimeOut(20000)
                //读取超时
                .readTimeOut(20000)
                //写超时
                .writeTimeOut(20000)
                .execute(bitmapCallback);
    }

}
