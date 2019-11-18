package com.fishinwater.util.callback;

import android.graphics.Bitmap;
import android.util.Log;

import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

public class MyBitmapCallback extends BitmapCallback {
    @Override
    public void onError(Call call, Exception e, int id)
    {
        // mText.setText("onError:" + e.getMessage());
    }

    @Override
    public void onResponse(Bitmap bitmap, int id)
    {
        Log.e("TAG", "onResponse：complete");
        // imageView.setImageBitmap(bitmap);
    }
}
