package com.fishinwater.plan.fragment.presenter;

import com.fishinwater.base.data.protocol.UserBean;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public interface IBasePresenter<T>  {
    /**
     * 获得当天所有计划
     * @param user
     */
    void getPlans(UserBean user);

    /**
     *
     * @param user
     */
    void getPlan(UserBean user);

    /**
     *
     * @param plan
     */
    void addPlan(T plan);

    /**
     *
     * @param plan
     */
    void updatePlan(T plan, int position);

    /**
     *
     * @param plan
     */
    void deletePlan(T plan);

}
