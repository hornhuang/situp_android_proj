package com.fishinwater.situp.factory;

import androidx.annotation.NonNull;

import com.fishinwater.situp.login.fragment.IOnResultListener;
import com.fishinwater.situp.login.model.IBaseLog;
import com.fishinwater.situp.login.presenter.IBasePresenter;
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
    public static IOnResultListener getFragmentInstance(@NonNull String classPathname) {
        try {
            return (IOnResultListener) Class.forName((String)PropertiesUtil.loadAssetsProperties(
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
    public static IBasePresenter getPresenterInstance(String classPathname) {
        try {
            return (IBasePresenter) Class.forName((String)PropertiesUtil.loadAssetsProperties(
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
    public static IOnResultListener getActivityInstance(String classPathname) {
        try {
            return (IOnResultListener) Class.forName((String)PropertiesUtil.loadAssetsProperties(
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
    public static IBaseLog getModelInstance(String classPathname) {
        try {
            return (IBaseLog) Class.forName((String)PropertiesUtil.loadAssetsProperties(
                    PropertiesUtil.CLASS_PROPERTIES)
                    .get(classPathname))
                    .newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
