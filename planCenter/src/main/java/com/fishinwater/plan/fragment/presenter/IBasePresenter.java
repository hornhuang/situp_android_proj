package com.fishinwater.plan.fragment.presenter;

import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.plan.fragment.Fragment.IFragmentView;
import com.fishinwater.plan.fragment.Fragment.PlanFragment;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public interface IBasePresenter<T>  {
    /**
     * 获得当天所有计划
     * @param user_id
     * @param callback
     */
    void getPlans(String user_id, String day_date,  IFragmentView<T> callback);

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
