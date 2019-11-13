package com.fishinwater.situp.andfix;

import android.content.Context;

import com.alipay.euler.andfix.AndFixManager;
import com.alipay.euler.andfix.patch.PatchManager;
import com.fishinwater.situp.util.PackageUtil;

/**
 * 管理 AndFix Api
 * 单例模式
 * @author fishinwater-1999
 * @version 2019-11-13
 */
public class AndFixPatchManager {

    private static AndFixPatchManager instance = null;

    private PatchManager manager = null;

    public static AndFixPatchManager getInstance() {
        if (instance == null) {
            synchronized (AndFixPatchManager.class) {
                instance = new AndFixPatchManager();
            }
        }
        return instance;
    }

    /**
     * 初始化 AndFix 方法
     * @param context
     */
    public void initPatch(Context context) {
        // 初始化
        manager = new PatchManager( context );
        manager.init(PackageUtil.getVersionName(context));
        // 完成所有类加载
        manager.loadPatch();
    }

    /**
     * 记载 AndFix patch 文件
     * @param path
     */
    public void addPatch(String path) {
        try {
            if (manager != null) {
                manager.addPatch(path);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
