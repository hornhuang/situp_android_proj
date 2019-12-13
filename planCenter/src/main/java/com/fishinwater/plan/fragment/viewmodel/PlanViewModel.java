package com.fishinwater.plan.fragment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.callback.PlansCallBack;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

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
        OkHttpUtils.post()
                .url(ApiUtils.PLAN_INSERT)
                .addParams("", "")
                .build().execute(new Callback() {

            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                planCallback.onFailed(e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {

            }
        });

        planCallback.onSucceed(null);

    }

    public void updatePlan(Plan plan, PlanCallback<Plan> planCallback) {
        planCallback.onSucceed(null);
    }

    public void deletePlan(Plan plan, PlanCallback<Plan> planCallback) {

        planCallback.onSucceed(null);
    }
}
