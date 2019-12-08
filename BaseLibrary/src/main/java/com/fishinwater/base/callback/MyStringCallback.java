package com.fishinwater.base.callback;

import android.util.Log;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * OkHttp-Utils 回调类
 * @fishinwater-1999
 */
public class MyStringCallback extends StringCallback
{

    private final String TAG = "MyStringCallback";

    @Override
    public void onBefore(Request request, int id)
    {
        // setTitle("loading...");
    }

    @Override
    public void onAfter(int id)
    {
        // setTitle("Sample-okHttp");
    }

    @Override
    public void onError(Call call, Exception e, int id)
    {
        e.printStackTrace();
        // mText.setText("onError:" + e.getMessage());
    }

    @Override
    public void onResponse(String response, int id)
    {
        Log.e(TAG, "onResponse：complete");
        // mText.setText("onResponse:" + response);

        switch (id)
        {
            case 100:
                // Toast.makeText(PostServer.this, "http", Toast.LENGTH_SHORT).show();
                break;
            case 101:
                // Toast.makeText(PostServer.this, "https", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void inProgress(float progress, long total, int id)
    {
        Log.e(TAG, "inProgress:" + progress);
        // mProgressBar.setProgress((int) (100 * progress));
    }
}
