package com.fishinwater.login;

import com.fishinwater.route.ARoute;
import com.fishinwater.route.IRoute;

/**
 * 在这里添加乳所有 Activity
 *
 * 这样不合适，要是有很多 Activity 就要反复改
 * 如果 Activity 改名，也要重新修改
 *
 * 所以引入 '注解'
 * @author fishinwater-1999
 * @version 2019-11-17
 */
public class ActivityUtil implements IRoute {
    @Override
    public void putActivity() {
        ARoute.getInstance().putActivity("login/login", LoginActivity.class);
        ARoute.getInstance().putActivity("login/resist", ResistActivity.class);
    }
}
