package com.fishinwater.plan.fragment.presenter;

import androidx.annotation.NonNull;

import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.callback.PlansCallBack;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.fragment.Fragment.IFragmentView;
import com.fishinwater.plan.fragment.model.DeletePlanModel;
import com.fishinwater.plan.fragment.model.GetPlanViewModel;
import com.fishinwater.plan.fragment.model.PublishPlanModel;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

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

    public Presenter(IFragmentView fragmentView) {
        this.fragmentView = fragmentView;
        this.publishPlanModel = new PublishPlanModel();
        this.getPlanViewModel = new GetPlanViewModel();
        this.deletePlanModel = new DeletePlanModel();
    }


    @Override
    public void getPlans(UserBean user) {
        getPlanViewModel.getPlans(user, new PlansCallBack<String>() {

            @Override
            public void onSucceed(List<String> list) {
                fragmentView.onSucceed(list);

            }

            @Override
            public void onFailed(String errMessage) {
                fragmentView.onFailure(errMessage);
            }
        });
    }

    @Override
    public void getPlan(UserBean user) {
        getPlanViewModel.getPlan(user, new PlanCallback<String>() {
            @Override
            public void onSucceed(String... collection) {

            }

            @Override
            public void onFailed(String errMessage) {

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
                fragmentView.onSucceed(response + "-- add Plan succeed Message");
            }

        });
    }

    @Override
    public void updatePlan(PlanBean plan, int position) {
        publishPlanModel.updatePlan(plan, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                fragmentView.onFailure("succeedMessage");
            }

            @Override
            public void onResponse(String response, int id) {
                fragmentView.onSucceed(plan);
                fragmentView.onSucceed("updatePlan Message");
            }

        });
    }

    @Override
    public void deletePlan(PlanBean plan) {
        deletePlanModel.deletePlan(plan, new PlanCallback<String>() {
            @Override
            public void onSucceed(String... collection) {
                fragmentView.onSucceed(plan);
                fragmentView.onSucceed("删除成功");
            }

            @Override
            public void onFailed(String errMessage) {
                fragmentView.onFailure("succeedMessage");
            }
        });
    }
}
