package com.fishinwater.plan.fragment;

import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public interface IFragmentView {

    /**
     * 成功返回计划列表
     * @param planList
     */
    void onSucceed(List<Plan> planList);

    /**
     * 失败返回失败信息
     * @param errMessage
     */
    void onFailure(String errMessage);

    /**
     * 获得当前用户
     * @return
     */
    UserBean getUserIfo();

}
