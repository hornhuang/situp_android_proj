package com.fishinwater.situp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fishinwater.situp.callback.MyStringCallback;
import com.fishinwater.situp.util.OkHttpUtil;
import com.fishinwater.situp.util.PropertiesUtil;

import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author fishinwater-1999
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpUtil.getDataByGET("http://192.168.43.138/SitUp/LoginServlet?username=admin&password=123", new MyStringCallback() {
            @Override
            public void onAfter(int id) {
                Log.d(TAG, id + "");
                super.onAfter(id);
            }

            @Override
            public void onBefore(Request request, int id) {
                Log.d(TAG, id + "");
                super.onBefore(request, id);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d(TAG, e + "");
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, response + "");
                super.onResponse(response, id);
            }
        });

    }

}
