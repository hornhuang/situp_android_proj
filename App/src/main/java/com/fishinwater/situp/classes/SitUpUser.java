package com.fishinwater.situp.classes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.StringDef;

import com.fishinwater.situp.classes.base.UserBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public class SitUpUser implements IObject{

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(value = {USER_ID, USER_NAME, USER_PASSWORD})
    public @interface Duration {}

    private static final String MY_PREFS_NAME = "";

    private static final String USER_IFO = "user_ifo";

    private static final String USER_ID = "user_Id";

    private static final String USER_NAME = "user_name";

    private static final String USER_PASSWORD = "user_password";

    /**
     * 获得当前用户的相关信息
     *
     * @param context
     * @param userPeremeter
     * @return
     */
    private static String getUserIfoFromPreference(Context context, @Duration String userPeremeter) {
        SharedPreferences preferences = context.getSharedPreferences(USER_IFO, Context.MODE_PRIVATE);
        return preferences.getString(userPeremeter, "");
    }

    /**
     * 获得当前登录的 User 对象
     * @param context
     * @return
     */
    public static UserBean getLocalUser(Context context) {
        UserBean userBean = new UserBean();
        SharedPreferences preferences = context.getSharedPreferences(USER_IFO, Context.MODE_PRIVATE);
        int userId = preferences.getInt(USER_ID, -1);
        if (userId == -1) {
            return userBean;
        }
        userBean.setId(userId);
        userBean.setPassword(preferences.getString(USER_PASSWORD, "undefined"));
        userBean.setName(preferences.getString(USER_NAME, "undefined"));
        return userBean;
    }

    /**
     * 检查用户目前是否登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_IFO, Context.MODE_PRIVATE);
        String userId = preferences.getString(USER_ID, null);
        if (userId == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<String[]> toList(Object object) {

        return null;
    }

    @Override
    public String toJsonString(Object object) {
        return null;
    }

    @Override
    public String toGetString(String[] strings) {
        return null;
    }


}
