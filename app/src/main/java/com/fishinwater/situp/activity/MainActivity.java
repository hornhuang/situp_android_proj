package com.fishinwater.situp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fishinwater.situp.R;
import com.fishinwater.situp.andfix.AndFixPatchManager;
import com.fishinwater.situp.callback.MyStringCallback;
import com.fishinwater.situp.login.view.LogActivity;
import com.fishinwater.situp.util.OkHttpUtil;
import com.fishinwater.situp.util.PropertiesUtil;

import java.io.File;
import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author fishinwater-1999
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    /**
     * Patch 文件，即补丁文件
     * 文件路径
     */
    private final String FILE_END = ".apatch";

    /**
     * Patch 文件存放路径
     * @param savedInstanceState
     */
    private String mPatchDir ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化 patch 文件路径
        mPatchDir = getExternalCacheDir().getAbsolutePath() + "apatch";
        // 创建文件夹，调用之后会在手机 /cache 下生成 /apatch 文件夹
        File file = new File(mPatchDir);
        if (file == null || file.exists()) {
            file.mkdir();
        }



        // startActivity(new Intent(this, LogActivity.class));

    }

    /**
     * 模拟 创建 Bug
     * @param view
     */
    public void createBug(View view) {
        String s = null;
        s.length();
    }

    /**
     * 模拟 修复 Bug
     * @param view
     */
    public void fixBug(View view) {
        // 使用 AndFix 需要生成 "带签名" 的 apk ，签名方法详见 "app.gradle" 中
        AndFixPatchManager.getInstance().addPatch(getPatchPath());
    }

    /**
     * 构造 Patch 文件名
     */
    private String getPatchPath() {
        return mPatchDir.concat("mainActivity").concat(FILE_END);
    }

    private void loginTest() {
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
