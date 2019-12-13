package com.fishinwater.base.common;

/**
 * @author fishinwater-1999
 * @version 2019-12-11
 */
public class ApiUtils {

    private static final String IPv4 = "47.107.132.227";

    // ============  User =============

    public static final String LOGIN_URL = "http://"+ IPv4 +":8080/SitUpWebServer/login";

    public static final String RESIST_URL = "http://"+ IPv4 +":8080/SitUpWebServer/adduser";

    // ============  Post ==============

    public static final String PUBLISH_POST = "http://"+ IPv4 +":8080/SitUpWebServer/addpost";

    public static final String GET_USER_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getuserposts";

    // ============  PLAN ==============

    public static final String PLAN_INSERT = "http://"+ IPv4 +":8080/SitUpWebServer/addpost";
}
