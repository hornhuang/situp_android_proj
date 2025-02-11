package com.fishinwater.plan.fragment.presenter;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.DayBean;
import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.DayModel;
import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.fragment.Fragment.IFragmentView;
import com.fishinwater.plan.fragment.model.DeletePlanModel;
import com.fishinwater.plan.fragment.model.GetPlanViewModel;
import com.fishinwater.plan.fragment.model.PublishPlanModel;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public class Presenter implements IBasePresenter<PlanBean>{

    private IFragmentView fragmentView;

    private PublishPlanModel publishPlanModel;

    private GetPlanViewModel getPlanViewModel;

    private DeletePlanModel deletePlanModel;

    private DayModel dayModel;

    public Presenter(IFragmentView fragmentView) {
        this.fragmentView = fragmentView;
        this.publishPlanModel = new PublishPlanModel();
        this.getPlanViewModel = new GetPlanViewModel();
        this.deletePlanModel = new DeletePlanModel();
        this.dayModel = new DayModel();
    }


    @Override
    public void getPlans(String user_id, String day_date, IFragmentView<PlanBean> callback) {
        dayModel.getDay(user_id, day_date, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains("{")) {
                    callback.onFailure("{");
                    return;
                }
                DayBean bean = JSONUtils.StringToObj(DayBean.class, response);
                if (bean.getDay_plans() != null && bean.getDay_plans().length() != 0) {
                    List<String> planIdList = JSONUtils.jsonStrtoList(String.class, bean.getDay_plans());
                    Observable.fromIterable(planIdList)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> getPlanViewModel.getPlan(s, new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id1) {
                                    callback.onFailure(e.getMessage());
                                }

                                @Override
                                public void onResponse(String response1, int id1) {
                                    callback.onGetSucceed(JSONUtils.StringToObj(PlanBean.class, response1));
                                }
                            }));
                } else {
                    callback.onFailure("今天还没有计划");
                }
            }
        });
    }

    @Override
    public void getPlan(String plan_id) {
        getPlanViewModel.getPlan(plan_id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                fragmentView.onGetSucceed(JSONUtils.StringToObj(PlanBean.class, response));
            }
        });
    }

    @Override
    public void addPlan(@NonNull PlanBean plan) {
        publishPlanModel.addPlan(plan, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                fragmentView.onFailure(e.getMessage() + "onError");
            }

            @Override
            public void onResponse(String response, int id) {
                String[] s = response.split("\n");
                for (String str : s) {
                    if (str.length() == 0) {
                        continue;
                    }
                    String[] strings = str.split(" ");
                    for (String string : strings) {
                        if (string.length() != 0) {
                            response = string;
                        }
                    }
                }
                plan.setPlan_id(response);
                fragmentView.onGetSucceed(plan);
                fragmentView.onSucceed(JSONUtils.objToString(plan));
            }

        });
    }

    @Override
    public void updatePlan(PlanBean plan, int position) {
        publishPlanModel.updatePlan(plan, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                fragmentView.onFailure(e.getMessage() + "on Error");
            }

            @Override
            public void onResponse(String response, int id) {
                fragmentView.onSucceed(plan);
                fragmentView.onSucceed(response);
            }

        });
    }

    @Override
    public void deletePlan(PlanBean plan) {
        deletePlanModel.deletePlan(plan, new PlanCallback<String>() {
            @Override
            public void onSucceed(String... collection) {
                fragmentView.onSucceed(plan);
                fragmentView.onSucceed(collection[0] + "删除成功");
            }

            @Override
            public void onFailed(String errMessage) {
                fragmentView.onFailure(errMessage + "on Failed");
            }
        });
    }
}
