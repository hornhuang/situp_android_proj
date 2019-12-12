package com.fishinwater.base.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class JSONUtils {

    public static <T> String arrayToJsonStr(List<T> list) {
        if (list != null) {
            return JSON.toJSONString(list);
        }
        return "";
    }

    public static <T> List<T> jsonStrtoList(Class<T> clazz, String jString) {
        List<T> list = new ArrayList<T>();
        list = JSONObject.parseArray(jString, clazz);
        return list;
    }

    public static <T> String objToString(T obj) {
        Gson gson = new Gson();
        String jString = gson.toJson(obj).toString();
        return jString;
    }

}
