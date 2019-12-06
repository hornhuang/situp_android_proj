package com.fishinwater.plan.fragment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.callback.PlansCallBack;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;

import java.util.ArrayList;
import java.util.List;

public class PlanViewModel extends ViewModel {

    public void postPlanList(UserBean user, PlansCallBack callBack) {
        List<Plan> plans = new ArrayList<>();
        Plan plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        plans.add(plan);
        plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        plans.add(plan);
        plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        plans.add(plan);
        plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        plans.add(plan);
        plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        plans.add(plan);
        callBack.onSucceed(plans);
    }

    public void postPlan(UserBean user, PlanCallback<Plan> planPlanCallback) {
        Plan plan = new Plan();
        plan.setName("算法撒娇哈是几点回家啊可是大");
        planPlanCallback.onSucceed(plan);
    }

    public void addPlan(Plan plan, PlanCallback<Plan> planCallback) {

        planCallback.onSucceed(null);

    }

    public void updatePlan(Plan plan, PlanCallback<Plan> planCallback) {
        planCallback.onSucceed(null);
    }

    public void deletePlan(Plan plan, PlanCallback<Plan> planCallback) {
        planCallback.onSucceed(null);
    }
}
