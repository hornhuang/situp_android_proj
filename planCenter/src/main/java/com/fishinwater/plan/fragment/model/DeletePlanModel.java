package com.fishinwater.plan.fragment.model;

import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.plan.callback.PlanCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public class DeletePlanModel {

    public void deletePlan(PlanBean plan, PlanCallback<String> planPlanCallback) {
        // http://localhost:8080/SitUpWebServer/deleteplan?plan_id=5bdfa8d2-e642-4f64-9f73-677b7ce3e3c4
        OkHttpUtils.post()
                .url(ApiUtils.DELETE_PLAN)
                .addParams("plan_id", "acabb7a5-c64a-4559-b0b4-3154b08b9380")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        planPlanCallback.onFailed(e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        planPlanCallback.onSucceed(response);

                    }
                });
    }

}
