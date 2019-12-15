package com.fishinwater.plan.fragment.model;

import com.fishinwater.base.OkHttpUtil;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.callback.PlansCallBack;
import com.fishinwater.plan.classes.base.Plan;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public class GetPlanViewModel {

    public void getPlans(String user_id, String day_date, PlansCallBack<String> planPlansCallBack) {

    }

    public void getPlan(String plan_id, StringCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_PLAN)
                .addParams("plan_id", plan_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onResponse(response, id);
                    }
                });
    }
}
