package com.fishinwater.situp.application;

import android.app.Application;
import android.content.Context;

import com.alipay.euler.andfix.AndFixManager;
import com.fishinwater.situp.andfix.AndFixPatchManager;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // 对外暴露 App
        instance = this;
        // 初始化 AndFix
        iniAndFix();
    }

    private void iniAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }

    public static Context getInstance() {
        return instance;
    }
}
