package com.fishinwater.situp;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.tinker.Log.MyLogImp;
import com.fishinwater.tinker.util.SampleApplicationContext;
import com.fishinwater.tinker.util.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * application类名。只能用字符串，这个MyApplication文件是不存在的，但可以在AndroidManifest.xml的application标签上使用（name）
 *
 * Created by leo
 * on 2019/9/11.
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = ".MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,// tinkerFlags
        // loaderClassName, 我们这里使用默认即可!（可不写）
        loaderClass = "com.tencent.tinker.loader.TinkerLoader",
        //tinkerLoadVerifyFlag
        loadVerifyFlag = false)
public class TinkerApplicationLike extends DefaultApplicationLike {

    private static final String TAG = "Tinker.SampleApplicationLike";

    private static TinkerApplicationLike mInstance;

    public TinkerApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        SampleApplicationContext.application = getApplication();
        SampleApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());
        // 可以将之前自定义的Application中onCreate()方法所执行的操作搬到这里...

        mInstance = this;
        ARouter.init(this.getApplication());
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    public static TinkerApplicationLike getInstance() {
        return mInstance;
    }

}
