package com.fishinwater.route;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fishinwater-1999
 * @version 2019-11-17
 */
public class ARoute {

    /**
     * map 这个 map 转载了 App 中所有的 activity 的 class 对象
     */
    private Map<String, Class<? extends Activity>> mActivityMap;

    /**
     * 这个 Map 在内存中绝对只有一个
     *
     * 这里来一个简单的单例
     */
    private static ARoute instance = new ARoute();

    private ARoute(){
        mActivityMap = new HashMap<>();
    }

    public static ARoute getInstance() {
        if (instance != null) {
            return instance;
        }else {
            return new ARoute();
        }
    }

    /**
     * 将 Activity 存入 map 中
     * @param path
     * @param clazz
     */
    public void putActivity(@NonNull String path, @NonNull Class<? extends Activity> clazz) {
        if (path.length() == 0) {
            return;
        }
        mActivityMap.put(path, clazz);
    }
}
