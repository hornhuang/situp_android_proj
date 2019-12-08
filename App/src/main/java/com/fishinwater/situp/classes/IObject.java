package com.fishinwater.situp.classes;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public interface IObject {

    /**
     * 将该 Object 数据成员，以数组形式存入 List
     * @return
     */
    List<String[]> toList(Object object);

    /**
     * 将该 Object 数据成员，转为 Json 串
     * @return
     */
    String toJsonString(Object object);

    /**
     * 将 toList 获得的 List 对象转为 Get 请求的 String
     * @param strings
     * @return
     */
    String toGetString(String[] strings);
}
