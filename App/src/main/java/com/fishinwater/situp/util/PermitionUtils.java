package com.fishinwater.situp.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fishinwater.situp.activity.MainActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * @author fishinwater-1999
 * @version 2019-11-14
 */
public class PermitionUtils {

    private static String TAG = "PermitionUtils";

    /**
     * 获取 SD card 读写权限
     * Tinker 热修复
     * @param AppCompatActivityContext
     * @return
     */
    public static void BugString(AppCompatActivity AppCompatActivityContext){
        RxPermissions rxPermissions = new RxPermissions(AppCompatActivityContext);
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
                            Log.e(TAG, file.getAbsolutePath());
                            TinkerInstaller.onReceiveUpgradePatch(AppCompatActivityContext, file.getAbsolutePath());
                        }
                    }

                } else {
                    Toast.makeText(AppCompatActivityContext, "热修复技术需要用到此权限", Toast.LENGTH_SHORT).show();
                }

            }
        }).isDisposed();
    }

}
