package com.fishinwater.situp.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fishinwater.situp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {

                    //        patch_signed_7zip.apk
                    String path = "/sdcard/Tinker/";
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File file = new File(path, "patch_signed_7zip.apk");
                    if (file.exists()) {
                        if (file.length() > 0) {
                            Log.e("我就想看看路径", file.getAbsolutePath());
                            TinkerInstaller.onReceiveUpgradePatch(MainActivity.this, file.getAbsolutePath());
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "热修复技术需要用到此权限", Toast.LENGTH_SHORT).show();
                }

            }
        }).isDisposed();


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "stupid", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
