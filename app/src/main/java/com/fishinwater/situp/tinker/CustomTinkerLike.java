package com.fishinwater.situp.tinker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.fishinwater.situp.tinker.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Tinker ApplicationLike
 *
 * 之所以用 ApplicationLike 而不直接用 Application 是因为 Tinker 需要监听 Application 的生命周期
 * 所以它使用 ApplicationLike 进行一个委托，使其可以在 ApplicationLike 中完成对生命周期的监听
 * 然后在不同的 Application 生命周期阶段，去做不同的初始化以及其他工作
 * 如果 Tinker 没有使用这种委托的模式，那么整个 Tinker 的初始化会非常复杂，而且需要使用该框架的程序员自己去做。
 *
 * @author fishinwater-1999
 * @version 2019-11-14
 */
@DefaultLifeCycle(application = ".MyTinkerApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class CustomTinkerLike extends ApplicationLike {

    private static CustomTinkerLike mInstance;

    public CustomTinkerLike(Application application,
                            int tinkerFlags,
                            boolean tinkerLoadVerifyFlag,
                            long applicationStartElapsedTime,
                            long applicationStartMillisTime,
                            Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
        mInstance = this;
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // 使应用支持分包
        MultiDex.install(base);
        // 初始化 Tinker
        TinkerManager.iniTinker(this);
    }

    public static Context getInstance() {
        return mInstance.getApplication().getApplicationContext();
    }
}
