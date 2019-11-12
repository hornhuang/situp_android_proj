package com.fishinwater.situp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class PropertiesUtil {

    /**
     * 类 配置文件（/src 目录下）
     * 得到 class.properties 配置文件中的所有配置属性
     *
     * 获得后调用范例 String IP = Utils.getNetConfigProperties().getProperty("IP");
     * @return Properties
     */
    public static Properties getNetConfigProperties() {
        Properties props = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/class.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}
