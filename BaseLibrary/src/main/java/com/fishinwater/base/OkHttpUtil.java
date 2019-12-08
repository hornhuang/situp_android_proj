package com.fishinwater.base;

import android.content.Context;

import com.fishinwater.base.callback.MyBitmapCallback;
import com.fishinwater.base.callback.MyFileCallBack;
import com.fishinwater.base.callback.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;

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
     * 发送字符串
     * 表单形式
     * @param url
     * @param userName
     * @param userPassword
     * @param stringCallback
     * @throws IOException
     */
    public static void sendDataByFormPOST(String url, String userName, String userPassword,  StringCallback stringCallback) {
        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", userName)
                .addParams("password", userPassword)
                .build()
                .execute(stringCallback);
    }

    /**
     * POST 上传
     * 上传 Gson 字符串
     * @param url
     * @param gsonString
     */
    public static void sendGsonByPOST(String url, String gsonString, MyStringCallback stringCallback) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(gsonString)
                .mediaType(JSON)
                .build()
                .execute(stringCallback);
    }

    /**
     * POST 上传
     * 上传单个文件
     * @param url
     * @param file
     * @param stringCallback
     */
    public static void sendFileByPOST(String url, File file, MyStringCallback stringCallback)
    {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(stringCallback);
    }

    /**
     * Post files
     * 以表单形式上传多个文件
     * @param fileList
     * @param url
     * @param params
     * @param headers
     * @param stringCallback
     */
    public static void sendFilesByFormPOST(List<File> fileList,
                                       String url,
                                       Map<String, String> params,
                                       Map<String, String> headers,
                                       MyStringCallback stringCallback) {
        PostFormBuilder builder = OkHttpUtils.post();
        for (File file : fileList) {
            builder.addFile("mFile", file.getName(), file);
        }
        builder.url(url)
            .params(params)
            .headers(headers)
            .build()
            .execute(stringCallback);
    }

    // ----------------------------- GET -----------------------------

    /**
     * get 请求数据
     * 在 Callback 中重写相应方法，获得放回结果
     * OkHttp 方法不能再主线程中执行/*所以要间隔子线程，并在其中执行
     * @param url 网络连接
     * @return
     * @throws IOException
     */
    public static void getResponseByFormGET(String url, String userName, String userPassword, MyStringCallback stringCallback) {
        OkHttpUtils
                .get()
                .url(url)
                .addParams("name", userName)
                .addParams("password", userPassword)
                .build()
                .execute(stringCallback);
    }

    /**
     * GET 数据
     * 获得路由返回信息
     * @param url
     * @param stringCallback
     */
    public static void getDataByGET(String url, MyStringCallback stringCallback)
    {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(stringCallback);
    }

    /**
     * GET 下载
     * 下载大文件
     * @param url
     * @param fileCallBack
     */
    public static void getFileByGET(String url, MyFileCallBack fileCallBack)
    {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(fileCallBack);
    }

    /**
     * GET 下载
     * 下载图片
     * @param url
     * @param context
     * @param bitmapCallback
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
