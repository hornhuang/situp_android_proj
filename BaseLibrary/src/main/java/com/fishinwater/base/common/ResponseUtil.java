package com.fishinwater.base.common;

/**
 * @author fishinwater-1999
 * @version 2019-12-11
 */
public class ResponseUtil {

    public static String JSON_SIMPLE = "{";

    public static String SUCCEED = "1";

    public static String FAILED = "-1";

    public static String WRONG_NAME = "-2";

    public static String WRONG_PASSWORD = "-3";

    public static String responseAdapter(String response) {
        switch (response) {
            case "1":
                return "";
            case "-1":
                return "";
            case "-2":
                return "";
            case "-3":
                return "";
            default:
                return "";
        }
    }

}
