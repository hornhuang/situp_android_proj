package com.fishinwater.base.common;

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
    public static String USER_KEY = "user";
    /**
     * value: @likes: 2019-12-15
     */
    public static String CURRENT_DAY = "day";
    /**
     * 用于判断 Day 对象
     * 如果今天的 TODAY_DATE - value 与所存值不同
     * 则更新，并提交新的 DAY 对象
     */
    public static String TODAY_DATE = "today_date";

    public static void putString(Activity context, String key, String value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putString(key, value).apply();
    }

    public static void putBoolean(Activity context, String key, Boolean value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putBoolean(key, value).apply();
    }

    public static void putFloat(Activity context, String key, Float value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putFloat(key, value).apply();
    }

    public static void putInt(Activity context, String key, Integer value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putInt(key, value).apply();
    }

    public static void putLong(Activity context, String key, Long value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putLong(key, value).apply();
    }

    public static void putStringSet(Activity context, String key, Set<String> value) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putStringSet(key, value).apply();
    }



    public static String getString(Activity context, String key) {
        SharedPreferences preferences = getPreference(context);
        return preferences.getString(key, "");
    }



    private static SharedPreferences.Editor getPreferenceEditor(Activity context) {
        SharedPreferences preferences = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

    private static SharedPreferences getPreference(Activity context) {
        SharedPreferences preferences = context.getPreferences(Context.MODE_PRIVATE);
        return preferences;
    }

}
