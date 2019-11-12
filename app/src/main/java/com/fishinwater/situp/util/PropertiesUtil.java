package com.fishinwater.situp.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class PropertiesUtil {

    private static final String TAG = "PropertiesUtil";

    public static final String CLASS_PROPERTIES = "class.properties";

    public static final String API_PROPERTIES = "api.properties";

    /**
     * 类 配置文件（/src 目录下）
     * 得到 apiurl.properties 配置文件中的所有配置属性
     *
     * 获得后调用范例 String IP = Utils.getNetConfigProperties().getProperty("IP");
     * @return Properties
     */
    public static Properties loadAssetsProperties(Context context, String arg) {
        Properties prop = null;
        prop = new Properties();
        //first load default properties
        try {
            prop.load(context.getAssets().open(arg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


    /**
    * 读取Res文件夹的文件
    */
    public static Properties loadResProperties(Context context, int id) {
        Properties prop = new Properties();
        //first load default properties
        try {
            prop.load(context.getResources().openRawResource(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


}
