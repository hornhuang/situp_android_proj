package com.fishinwater.base.common;

/**
 * @author fishinwater-1999
 * @version 2019-12-11
 */
public class ApiUtils {

    private static final String IPv4 = "47.107.132.227";

    // ============  User ==============

    public static final String LOGIN_URL = "http://"+ IPv4 +":8080/SitUpWebServer/login";

    public static final String RESIST_URL = "http://"+ IPv4 +":8080/SitUpWebServer/adduser";

    // ============  Plan ==============

    public static final String PUBLISH_PLAN = "http://"+ IPv4 +":8080/SitUpWebServer/AddPlan";

    // ============  Post ==============

    public static final String GET_POST_BY_ID = "http://"+ IPv4 +":8080/SitUpWebServer/getpost";

    public static final String PUBLISH_POST = "http://"+ IPv4 +":8080/SitUpWebServer/addpost";

    public static final String GET_USER_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getuserposts";

    public static final String GET_USER_FAVORITE_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getuserfavorite";

    public static final String GET_USER_COLLECT_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getusercollections";

    // ============  PLAN ==============

    public static final String PLAN_INSERT = "http://"+ IPv4 +":8080/SitUpWebServer/addpost";
}
