package com.fishinwater.situp;

import android.app.Application;
import android.content.Context;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        if (instance == null) {
            super.onCreate();
            instance = this;
        }
    }

    public static Context getInstance() {
        return instance;
    }
}
