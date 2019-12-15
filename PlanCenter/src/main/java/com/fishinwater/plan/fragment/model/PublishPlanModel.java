package com.fishinwater.plan.fragment.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.plan.callback.PlanCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PublishPlanModel extends ViewModel {

    public void addPlan(PlanBean plan, StringCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.PUBLISH_PLAN)
                .addParams("plan_title", "" + plan.getPlan_title())
                .addParams("plan_content", "" + plan.getPlan_content())
                .addParams("plan_date", "" + plan.getPlan_date())
                .addParams("plan_start_date", "" + plan.getPlan_start_date())
                .addParams("plan_end_date", "" + plan.getPlan_end_date())
                .addParams("plan_score", "" + plan.getPlan_score())
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

    public void updatePlan(PlanBean plan, StringCallback callback) {
        // plan_start_date=qwe&plan_end_date=qwe&plan_score=0
        OkHttpUtils.post()
                .url(ApiUtils.UPDATA_PLAN)
                .addParams("plan_id", "b99d40bd-195a-4ab7-8e2a-762b87922462")
                .addParams("plan_title", "" + plan.getPlan_title())
                .addParams("plan_content", "" + plan.getPlan_content())
                .addParams("plan_date", "" + plan.getPlan_date())
                .addParams("plan_start_date", "" + plan.getPlan_start_date())
                .addParams("plan_end_date", "" + plan.getPlan_end_date())
                .addParams("plan_score", "" + plan.getPlan_score())
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
