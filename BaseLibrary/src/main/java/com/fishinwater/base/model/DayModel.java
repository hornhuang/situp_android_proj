package com.fishinwater.base.model;

import android.util.Log;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.DayBean;
import com.fishinwater.base.data.protocol.PlanBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public class DayModel {


    public void publishDay(String user_id, final IBaseCallback<String> callback) {
        Log.d("123123", user_id + "=========DayModel");
        OkHttpUtils.post()
                .url(ApiUtils.PUBLISH_DAY)
                .addParams("user_id", user_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(response);
                    }
                });
    }

    public void getDay(String user_id, String day_date, final StringCallback callback) {
        if (user_id == null || user_id.length() == 0 || day_date == null || day_date.length() == 0) {
            return;
        }
        Log.d("123123", user_id + "||||||||||||" + day_date);
        OkHttpUtils.post()
                .url(ApiUtils.GET_DAY)
                .addParams("user_id", user_id)
                .addParams("day_date", day_date)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("123123", e.getMessage() + "-------------------err-------------------");
                        callback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("123123", "--------------------------------------");
                        Log.d("123123", response + "------------------response--------------------");
                        callback.onResponse(response, id);
                    }
                });
    }

    public void updateDay(String user_id, String day_date, final List<PlanBean> planList, final StringCallback callback) {
        Log.d("123123", user_id + "+++++++++++++++++++++++++++" + day_date);
        getDay(user_id, day_date, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (planList == null || response == null || response.length() == 0) {
                    return;
                }
                Log.d("123123", response + "+++++++++++++++++++++++++++");
                DayBean dayBean = JSONUtils.StringToObj(DayBean.class, response);
                List<String> list = new ArrayList<>();
                for (PlanBean bean : planList) {
                    list.add(bean.getPlan_id());
                }
                OkHttpUtils.post()
                        .url(ApiUtils.UPDATE_DAY)
                        .addParams("day_id", dayBean.getDay_id())
                        .addParams("day_plans", JSONUtils.arrayToJsonStr(list))
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
        });
    }

}
