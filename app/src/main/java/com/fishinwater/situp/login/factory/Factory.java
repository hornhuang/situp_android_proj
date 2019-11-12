package com.fishinwater.situp.login.factory;

import androidx.annotation.NonNull;

import com.fishinwater.situp.App;
import com.fishinwater.situp.login.fragment.IBaseFragment;
import com.fishinwater.situp.util.PropertiesUtil;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class Factory {

    /**
     * 获得 Fragment 实例
     * @param classPathname
     * @return
     */
    public static IBaseFragment getFragmentInstance(@NonNull String classPathname) {
        try {
            return (IBaseFragment) Class.forName((String)PropertiesUtil.loadAssetsProperties(App.getInstance(),
                    PropertiesUtil.CLASS_PROPERTIES)
                    .get(classPathname))
                    .newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得 Presenter 实例
     * @param classPathname
     * @return
     */
    public static IBaseFragment getPresenterInstance(String classPathname) {
        try {
            return (IBaseFragment) Class.forName((String)PropertiesUtil.loadAssetsProperties(App.getInstance(),
                    PropertiesUtil.CLASS_PROPERTIES)
                    .get(classPathname))
                    .newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得 Activity 实例
     * @param classPathname
     * @return
     */
    public static IBaseFragment getActivityInstance(String classPathname) {
        try {
            return (IBaseFragment) Class.forName((String)PropertiesUtil.loadAssetsProperties(App.getInstance(),
                    PropertiesUtil.CLASS_PROPERTIES)
                    .get(classPathname))
                    .newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得 Model 实例
     * @param classPathname
     * @return
     */
    public static IBaseFragment getModelInstance(String classPathname) {
        try {
            return (IBaseFragment) Class.forName((String)PropertiesUtil.loadAssetsProperties(App.getInstance(),
                    PropertiesUtil.CLASS_PROPERTIES)
                    .get(classPathname))
                    .newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
