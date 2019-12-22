package com.fishinwater.base.common.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author fishinwater-1999
 * @version 2019-12-18
 */
public class PreferencesActivityBuilder extends Activity {

    public SharedPreferences.Editor installPreference(String preferenceName) {
        SharedPreferences preferences = getSharedPreferences(preferenceName, Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

}
