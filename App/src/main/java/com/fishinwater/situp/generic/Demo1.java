package com.fishinwater.situp.generic;

import com.bumptech.glide.Glide;

import java.util.HashMap;

/**
 * 泛型：类型限定的意思
 * 可以把集合里面，元素的类型，推辞到创建集合的时候
 *
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public class Demo1 {

    HashMap<String, Integer> map;

    Demo1() {
        map = new HashMap<>();
        map.put("三连", 100);
        map.put("点赞", 1000);
    }

}
