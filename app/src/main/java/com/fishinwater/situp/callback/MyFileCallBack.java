package com.fishinwater.situp.callback;

import android.os.Environment;
import android.util.Log;

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

public class MyFileCallBack extends FileCallBack {

    private final String TAG = "MyFileCallBack";

    public MyFileCallBack(String destFileDir, String destFileName) {
        super(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar");
    }

    @Override
    public void onBefore(Request request, int id)
    {
    }

    @Override
    public void inProgress(float progress, long total, int id)
    {
        // mProgressBar.setProgress((int) (100 * progress));
        Log.e(TAG, "inProgress :" + (int) (100 * progress));
    }

    @Override
    public void onError(Call call, Exception e, int id)
    {
        Log.e(TAG, "onError :" + e.getMessage());
    }

    @Override
    public void onResponse(File file, int id)
    {
        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
    }

}
