package com.fishinwater.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-11
 */
public class JsonUtils {

    public static <T> List<T> jsonStrToList(String json, Class<T> clazz) {
        List<T> list ;
        list = JSONObject.parseArray(json, clazz);
        return list;
    }

    public static <T> String listToGsonStr(List<T> objList) {
        return JSON.toJSON(objList).toString();
    }

}
