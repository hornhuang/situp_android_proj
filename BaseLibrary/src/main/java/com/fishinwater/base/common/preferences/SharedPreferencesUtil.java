package com.fishinwater.base.common.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class SharedPreferencesUtil {
    /**
     * value: JSON String
     */
    public static String USER_ID = "user_id";
    /**
     * value: @likes: 2019-12-15
     */
    public static String CURRENT_DAY = "day";
    /**
     * value: @likes: 2019-12-15
     */
    public static String BACKGROUND_PIC = "background";
    /**
     * 用于判断 Day 对象
     * 如果今天的 TODAY_DATE - value 与所存值不同
     * 则更新，并提交新的 DAY 对象
     */
    @Deprecated
    public static String TODAY_DATE = "today_date";

    /**
     * SharedPreferences 的名字
     */
    public static String PRE_NAME_SITUP = "sit_up";


    public static String PRE_NAME_USER = "sit_up_user";


    public static String PRE_NAME_POST = "sit_up_post";

    public static boolean isLogedin(Activity activity, String  preferencesName) {
        if (getString(activity,preferencesName,  USER_ID) != null && getString(activity,preferencesName,  USER_ID).length() != 0){
            return true;
        }else {
            return false;
        }
    }

    public static PreferencesActivityBuilder init() {
        return new PreferencesActivityBuilder();
    }

    public static void putString(Activity context, String  preferencesName, String key, String value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putString(key, value).apply();
    }

    public static void putBoolean(Activity context, String  preferencesName, String key, Boolean value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putBoolean(key, value).apply();
    }

    public static void putFloat(Activity context, String  preferencesName, String key, Float value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putFloat(key, value).apply();
    }

    public static void putInt(Activity context, String  preferencesName, String key, Integer value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putInt(key, value).apply();
    }

    public static void putLong(Activity context, String  preferencesName, String key, Long value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putLong(key, value).apply();
    }

    public static void putStringSet(Activity context, String  preferencesName, String key, Set<String> value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context, preferencesName);
        editor.putStringSet(key, value).apply();
    }



    public static String getString(Activity context, String  preferencesName, String key) {
        SharedPreferences preferences = getPreference(context, preferencesName);
        return preferences.getString(key, "");
    }



    private static SharedPreferences.Editor getPreferenceEditor(Activity context, String  preferencesName) {
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

    private static SharedPreferences getPreference(Activity context, String  preferencesName) {
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return preferences;
    }

}
