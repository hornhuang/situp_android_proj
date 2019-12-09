package com.fishinwater.plan.fragment.presenter;

import com.fishinwater.plan.callback.PlanCallback;
import com.fishinwater.plan.callback.PlansCallBack;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;
import com.fishinwater.plan.fragment.Fragment.IFragmentView;
import com.fishinwater.plan.fragment.viewmodel.PlanViewModel;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public class Presenter implements IBasePresenter<Plan>{

    private IFragmentView fragmentView;
    private PlanViewModel model;

    public Presenter(IFragmentView fragmentView) {
        this.fragmentView = fragmentView;
        this.model = new PlanViewModel();
    }


    @Override
    public void getPlans(UserBean user) {
        model.postPlanList(user, new PlansCallBack<Plan>() {

            @Override
            public void onSucceed(List<Plan> list) {
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
        model.postPlan(user, new PlanCallback<Plan>() {
            @Override
            public void onSucceed(Plan collection) {

            }

            @Override
            public void onFailed(String errMessage) {

            }
        });
    }

    @Override
    public void addPlan(Plan plan) {
        model.addPlan(plan, new PlanCallback<Plan>() {
            @Override
            public void onSucceed(Plan collection) {
                fragmentView.onSucceed("succeed Message");
            }

            @Override
            public void onFailed(String errMessage) {
                fragmentView.onFailure("succeedMessage");
            }
        });
    }

    @Override
    public void updatePlan(Plan plan, int position) {
        model.updatePlan(plan, new PlanCallback<Plan>() {
            @Override
            public void onSucceed(Plan plan) {
                fragmentView.onSucceed(plan);
                fragmentView.onSucceed("updatePlan Message");
            }

            @Override
            public void onFailed(String errMessage) {
                fragmentView.onFailure("succeedMessage");
            }
        });
    }

    @Override
    public void deletePlan(Plan plan) {
        model.deletePlan(plan, new PlanCallback<Plan>() {
            @Override
            public void onSucceed(Plan collection) {
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
