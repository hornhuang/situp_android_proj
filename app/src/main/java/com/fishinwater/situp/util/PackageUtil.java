package com.fishinwater.situp.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author fishinwater-1999
 * @version 2019-11-13
 */
public class PackageUtil {

    public static String getVersionName(Context context) {
        String mVersionName = "1.0.0";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo ifo = manager.getPackageInfo(context.getPackageName(), 0);
            mVersionName = ifo.versionName;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mVersionName;
    }

}
