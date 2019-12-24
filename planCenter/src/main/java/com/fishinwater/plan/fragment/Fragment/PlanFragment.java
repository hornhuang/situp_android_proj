package com.fishinwater.plan.fragment.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.DayBean;
import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.DayModel;
import com.fishinwater.plan.R;
import com.fishinwater.plan.fragment.adapter.PlanAdapter;
import com.fishinwater.plan.fragment.presenter.Presenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

@Route(path = RouteUtils.PlanFragment)
public class PlanFragment extends BaseFragment implements View.OnClickListener, IFragmentView<PlanBean>{

    /** 主界面
     *
     */
    SwipeRefreshLayout refreshLayout;

    LinearLayout linearlayout;

    RecyclerView recyclerView;
    TextView conclusion;

    FloatingActionButton mAddFab;

    /** 隐藏页面
     *
     *  内容：昨天
     */
    RelativeLayout holdView;

    TextView mYesConclusion;

    ImageView mYesBackground;

    /**
     * RecyclerView
     * @param savedInstanceState
     */

    private List<PlanBean> planList;

    /**
     * 确保 planList 有序
     */
    private HashMap<Integer, PlanBean> planMap;

    /**
     * 拷贝自 day -> planList
     */
    private PlanAdapter planAdapter;

    public static PlanFragment newInstance(String from) {
        return new PlanFragment();
    }

    DayModel mDayModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.plans_fragment, container, false);
        mDayModel = new DayModel();
        /**
         * 关闭初始化
         * 作废该方法
         */
        iniViews(view);
        iniRecycler();
        return view;
    }

    private void iniViews(View view){
        refreshLayout = view.findViewById(R.id.refresh);
        linearlayout = view.findViewById(R.id.linearlayout);
        recyclerView = view.findViewById(R.id.recycler);
        conclusion = view.findViewById(R.id.conclusion);
        mAddFab = view.findViewById(R.id.fab);

        holdView = view.findViewById(R.id.yes_page);
        mYesConclusion = view.findViewById(R.id.yes_conclusion);
        mYesBackground = view.findViewById(R.id.yes_bac);

        setHasOptionsMenu(true);

        mAddFab.setOnClickListener(this);
        conclusion.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            planList.clear();
            load();
        });

    }

    /**
     * 下载计划
     *
     * 从服务器下载今天计划
     */
    private void load() {
        presenter.getPlans(SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID),
             SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.CURRENT_DAY ),
             this);
    }

    private void iniRecycler(){
        planList = new ArrayList<>();
        planMap  = new HashMap<Integer, PlanBean>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        planAdapter = new PlanAdapter(planList, getActivity(), this);
        recyclerView.setAdapter(planAdapter);
        load();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fab) {
            dialogShow();
        }else  if (id == R.id.conclusion) {
            showEditDialog();
        }
    }

    /**
     * 保存计划内容到服务器
     *
     * @param p
     */
    private void savePlan(PlanBean p){
        presenter.addPlan(p);
    }

    /**
     * 提交计划 Dialog
     *
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     */
    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_push_item, null);
        final EditText fromHour    = view.findViewById(R.id.start_hour);
        final EditText fromMinutes = view.findViewById(R.id.start_minutes);
        final EditText toHour      = view.findViewById(R.id.end_hour);
        final EditText toMinutes   = view.findViewById(R.id.end_minutes);
        final EditText planName    = view.findViewById(R.id.plan_name);
        Button btn_sure   = view.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = view.findViewById(R.id.dialog_btn_cancel);
        fromHour.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fromMinutes.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        toHour.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        toMinutes.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        //自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.getWindow().setContentView(view);
        //可以设置显示的位置
        btn_sure.setOnClickListener(v -> {
                PlanBean plan = new PlanBean();
                plan.setPlan_start_date(fromHour.getText().toString() + ":" + fromMinutes.getText().toString());
                plan.setPlan_end_date(toHour.getText().toString() + ":" + toMinutes.getText().toString());
                plan.setPlan_title(planName.getText().toString());
                savePlan(plan);
                dialog.dismiss();
            });

        btn_cancel.setOnClickListener(arg0 -> dialog.dismiss());

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     *
     * @return
     */
    private void showEditDialog(){
        final EditText et = new EditText(getActivity());

        Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("今天总结一下：")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(et)
                .setPositiveButton("确定", (dialog1, which) -> {
                    String input = et.getText().toString();
                    conclusion.setText(input);
                    //upDate();
                })
                .setNegativeButton("取消", null)
                .show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onBind() {
        presenter = new Presenter(this);
    }

    @Override
    protected void unBind() {
        presenter = null;
    }

    @Override
    public void onGetSucceed(PlanBean plan) {
        if (plan == null) {
            return;
        }
        this.planList.add(plan);
        planAdapter.notifyDataSetChanged();
        String user_id = SharedPreferencesUtil.getString( getActivity(),
                SharedPreferencesUtil.PRE_NAME_SITUP,
                SharedPreferencesUtil.USER_ID);
        String day_date = DateUtils.getDayDateStr();
        mDayModel.updateDay(user_id, day_date, planList, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onResponse(String response, int id) {
                refreshLayout.setRefreshing(false);

            }
        });
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onSucceed(String succeedMessage) {
        refreshLayout.setRefreshing(false);
        toast(succeedMessage);
    }

    @Override
    public void onSucceed(PlanBean plan) {
        refreshLayout.setRefreshing(false);
        planList.remove(plan);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSucceed(PlanBean plan, int position) {
        refreshLayout.setRefreshing(false);
        planList.set(position, plan);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String errMessage) {
        new Exception("errMessage").printStackTrace();
        toast(errMessage);
        if (errMessage != null && errMessage.contains("{")) {
            new DayModel().publishDay(SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID), new IBaseCallback<String>() {
                @Override
                public void onSucceed(String obj) {
                    SharedPreferencesUtil.putString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.CURRENT_DAY, DateUtils.getDayDateStr());
                }

                @Override
                public void failed(String err) {

                }
            });
        }
    }

    @Override
    public UserBean getUserIfo() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        UserBean userBean = new UserBean();
        userBean.setUser_id(preferences.getString("user_id", ""));
        return userBean;
    }
}
