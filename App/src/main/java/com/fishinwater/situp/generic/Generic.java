package com.fishinwater.situp.generic;

import java.util.ArrayList;

/**
 * 泛型：类型限定的意思
 *
 * 本质工作：可以把集合里面，元素的类型，推辞到创建集合的时候
 *
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public class Generic {

    public static void main(String[] args) {
        // 此时什么类型都能存
        ArrayList list = new ArrayList();
        list.add(1);
        list.add("aaa");
        // 当限定为 String 时，传 int 就会报错
        ArrayList<String> list2 = new ArrayList();
        // list2.add(1);
        list2.add("aaa");

    }

}
