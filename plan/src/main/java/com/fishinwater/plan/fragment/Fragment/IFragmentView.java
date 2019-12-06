package com.fishinwater.plan.fragment.Fragment;

import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public interface IFragmentView<T>  {

    /**
     * 成功
     */
    void onSucceed(String succeedMessage);

    /**
     * 成功
     * @param plan 操作的计划项
     */
    void onSucceed(Plan plan);

    /**
     * 成功
     * @param plan 操作的计划项
     * @param position plan 的位置
     */
    void onSucceed(Plan plan, int position);

    /**
     * 成功返回计划列表
     * @param planList
     */
    void onSucceed(List<T> planList);

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
