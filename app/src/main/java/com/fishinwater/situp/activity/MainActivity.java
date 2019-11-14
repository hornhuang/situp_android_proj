package com.fishinwater.situp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fishinwater.situp.R;
import com.fishinwater.situp.callback.MyStringCallback;
import com.fishinwater.situp.classes.UserBean;
import com.fishinwater.situp.login.view.LogActivity;
import com.fishinwater.situp.tinker.TinkerManager;
import com.fishinwater.situp.util.BugFixText;
import com.fishinwater.situp.util.OkHttpUtil;
import com.fishinwater.situp.util.PropertiesUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author fishinwater-1999
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    /**
     * 服务器 测试
     */
    private final String URL = "http://192.168.0.103/SitUp/LoginServlet";

    /**
     * Patch 文件，即补丁文件
     * 文件路径
     */
    private final String FILE_END = ".apk";

    /**
     * Patch 文件存放路径
     * @param savedInstanceState
     */
    private String mPatchDir ;

    @BindView(R.id.result_text)
    public TextView mGetText;

    @BindView(R.id.download)
    public Button mDownloadBtn;

    @BindView(R.id.fix)
    public Button mFixBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 初始化 patch 文件路径
        mPatchDir = getExternalCacheDir().getAbsolutePath() + "/tpatch/";
        // 创建文件夹，调用之后会在手机 /cache 下生成 /tpatch 文件夹
        File file = new File(mPatchDir);
        if (!file.exists()) {
            if (!file.mkdir()){
                Log.d(TAG, "build failed");
            }
        }

//        startActivity(new Intent(this, LogActivity.class));

    }

    /**
     * 模拟 创建 Bug
     * @param view
     */
    @OnClick(R.id.download)
    public void createBug(View view) {
        Toast.makeText(this, "This is Bug String : " + BugFixText.BugString(), Toast.LENGTH_LONG).show();
    }

    /**
     *
     * @param v
     */
    @OnClick(R.id.fix)
    public void loadPatch(View v) {
        TinkerManager.loadPatch(getPackageCodePath());
        Toast.makeText(this, "已修复 Bug -> 文件存在：" + new File(getPatchName()).exists(), Toast.LENGTH_LONG).show();
    }

    /**
     * 构造 Patch 文件名
     */
    private String getPatchName() {
        return mPatchDir.concat("situp").concat(FILE_END);
    }


}
