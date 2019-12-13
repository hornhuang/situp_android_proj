package com.fishinwater.base.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class DateUtils {

    public static String getDayDateStr() {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sFormat.format(new Date());
    }

    public static String getDayTimeStr() {
        SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
        return sFormat.format(new Date());
    }

}
