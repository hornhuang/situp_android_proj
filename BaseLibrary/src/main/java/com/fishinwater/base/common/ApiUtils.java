package com.fishinwater.base.common;

/**
 * @author fishinwater-1999
 * @version 2019-12-11
 */
public class ApiUtils {

    private static final String IPv4 = "47.107.132.227";

    public static final String BASE_URL = "http://"+ IPv4 +":8080/SitUpWebServer/";

    // ============  User ==============

    public static final String LOGIN_URL = "http://"+ IPv4 +":8080/SitUpWebServer/login";

    public static final String RESIST_URL = "http://"+ IPv4 +":8080/SitUpWebServer/adduser";

    public static final String GET_USER = "http://"+ IPv4 +":8080/SitUpWebServer/getuser";

    public static final String UPDATE_USER = "http://"+ IPv4 +":8080/SitUpWebServer/update";

    public static final String UPDATE_USER_INTRODUCE = "http://"+ IPv4 +":8080/SitUpWebServer/UpdateUserIntroduceServlet";

    public static final String UPDATE_USER_IMG = "http://"+ IPv4 +":8080/SitUpWebServer/updateuserheadimg";

    // ============  Plan ==============

    public static final String PUBLISH_PLAN = "http://"+ IPv4 +":8080/SitUpWebServer/AddPlan";

    public static final String UPDATA_PLAN = "http://"+ IPv4 +":8080/SitUpWebServer/updateplan";

    public static final String DELETE_PLAN = "http://"+ IPv4 +":8080/SitUpWebServer/deleteplan";

    public static final String GET_PLAN = "http://"+ IPv4 +":8080/SitUpWebServer/getplan";

    // ============  Post ==============

    public static final String GET_POST_BY_ID = "http://"+ IPv4 +":8080/SitUpWebServer/getpost";

    public static final String PUBLISH_POST = "http://"+ IPv4 +":8080/SitUpWebServer/addpost";

    public static final String GET_USER_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getuserposts";

    public static final String GET_USER_FAVORITE_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getuserfavorite";

    public static final String GET_USER_COLLECT_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getusercollections";

    public static final String GET_ALL_POSTS = "http://"+ IPv4 +":8080/SitUpWebServer/getpostsbypage";

    // ============  DAY  ==============

    public static final String PUBLISH_DAY = "http://"+ IPv4 +":8080/SitUpWebServer/addday";

    public static final String GET_DAY = "http://"+ IPv4 +":8080/SitUpWebServer/getday";

    public static final String UPDATE_DAY = "http://"+ IPv4 +":8080/SitUpWebServer/updateday";

    // ============  Collect  =============

    public static final String JUDGE_COLLECT = "http://"+ IPv4 +":8080/SitUpWebServer/ispostusercolleted";

    public static final String ADD_COLLECT = "http://"+ IPv4 +":8080/SitUpWebServer/addcollection";

    // ============  Favorite =============

    public static final String JUDGE_FAVORITE = "http://"+ IPv4 +":8080/SitUpWebServer/ispostuserfavorited";

    public static final String ADD_FAVORITE = "http://"+ IPv4 +":8080/SitUpWebServer/addfavorite";

    // ============  Favorite =============

    public static final String GET_PICTURE = "http://"+ IPv4 +":8080/SitUpWebServer/GetPicturesByFlagServlet";

}
