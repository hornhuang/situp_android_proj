package com.fishinwater.plan.fragment.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.fishinwater.plan.R;
import com.fishinwater.plan.classes.base.Day;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.plan.classes.base.UserBean;
import com.fishinwater.plan.fragment.adapter.PlanAdapter;
import com.fishinwater.plan.fragment.presenter.Presenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlanFragment extends BaseFragment implements View.OnClickListener, IFragmentView<Plan> {

    private final String TAG = "PlanFragment";

    private boolean pageFlag = false;

    /** 主界面
     *
     */
    SwipeRefreshLayout refreshLayout;

    LinearLayout linearlayout;

    RecyclerView recyclerView;

    TextView conclusion;

    Button save;

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
    private Day day, yesDay;

    private List<Plan> planList;

    /**
     * 确保 planList 有序
     */
    private HashMap<Integer, Plan> planMap;

    /**
     * 拷贝自 day -> planList
     */
    private List<String> planIds;

    private PlanAdapter planAdapter;

    public static PlanFragment newInstance(String from) {
        return new PlanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.plan_fragment, container, false);
        /**
         * 关闭初始化
         * 作废该方法
         */
        iniViews(view);
        iniRecycler();
        // load();
        return view;
    }

    private void iniViews(View view){
        refreshLayout = view.findViewById(R.id.refresh);
        linearlayout = view.findViewById(R.id.linearlayout);
        recyclerView = view.findViewById(R.id.recycler);
        conclusion = view.findViewById(R.id.conclusion);
        save = view.findViewById(R.id.save_change);
        mAddFab = view.findViewById(R.id.fab);

        holdView = view.findViewById(R.id.yes_page);
        mYesConclusion = view.findViewById(R.id.yes_conclusion);
        mYesBackground = view.findViewById(R.id.yes_bac);

        setHasOptionsMenu(true);

        save.setOnClickListener(this);
        mAddFab.setOnClickListener(this);
        conclusion.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(() -> {
            planList.clear();
            // load();
        });

    }

    /**
     * 下载计划
     *
     * 从服务器下载今天计划
     */
//    private void load() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        BmobDate bmobCreatedAtDate = new BmobDate(cal.getTime());
//
//        BmobQuery<Day> categoryBmobQuery = new BmobQuery<>();
//        categoryBmobQuery.addWhereGreaterThanOrEqualTo("createdAt", bmobCreatedAtDate);
//        categoryBmobQuery.findObjects(new FindListener<Day>() {
//            @Override
//            public void done(List<Day> object, BmobException e) {
//                if (e == null) {
//                    if (object.size() == 0){
//                        // ...
//                        return;
//                    }else {
//                        if (object.size() > 1){
//                            yesDay = object.get(object.size()-2);
//                        }else {
//                            yesDay = new Day();
//                        }
//                        day    = object.get(object.size()-1);
//                        conclusion.setText(day.getConclusion());
//                        mYesConclusion.setText(yesDay.getConclusion());
//                        planIds = new ArrayList<>();
//                        planIds.addAll(day.getPlanList());
//                        Observable.fromIterable(day.getPlanList())
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(Schedulers.io())
//                                .subscribe(new Observer<String>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//                                        refreshLayout.setRefreshing(true);
//                                    }
//
//                                    @Override
//                                    public void onNext(String s) {
//                                        getPlan(s);
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//                                        refreshLayout.setRefreshing(false);
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//                                        refreshLayout.setRefreshing(false);
//                                    }
//                                });
//                    }
//
//                } else {
//                    Log.d(TAG, "failed" + e.toString());
//                }
//            }
//        });
//    }

    /**
     * 查询计划
     */
//    private void getPlan(String objectId) {
//        BmobQuery<Plan> bmobQuery = new BmobQuery<>();
//        bmobQuery.getObject(objectId, new QueryListener<Plan>() {
//            @Override
//            public void done(Plan plan,BmobException e) {
//                if(e==null){
//                    // 有序插入
//                    String objectId = planIds.get(0);
//                    // 如果为有序序列下一个，则进行插入
//                    // 否则插入哈希表
//                    if (objectId.equals(plan.getId())){
//                        planList.add(plan);
//                        planAdapter.notifyDataSetChanged();
//                        // 插入后移除有序序列第一项
//                        planIds.remove(0);
//                        // 由于下一项可能在之前已经到达，所以要在哈希表中查询
//                        objectId = planIds.get(0);
//                        plan = planMap.get(objectId);
//                        // 如果下一项在哈希表中，则继续查下下项是否也在
//                        // 以此类推到哈希表中找不到有序序列下一项为止
//                        while (plan != null){
//                            planList.add(plan);
//                            planAdapter.notifyDataSetChanged();
//                            planIds.remove(0);
//                            objectId = planIds.get(0);
//                            plan = planMap.get(objectId);
//                        }
//                    }else {
//                        // 不为下一个计划
//                        // 插入哈希表
//                        planMap.put(plan.getId(), plan);
//                    }
//                }else{
//
//                }
//            }
//        });
//    }

    private void iniRecycler(){
        planList = new ArrayList<>();
        planMap  = new HashMap<>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        planAdapter = new PlanAdapter(planList, getActivity(), this);
        recyclerView.setAdapter(planAdapter);
        presenter.getPlans(new UserBean());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save_change) {
            pushNewDay();
        }else  if (id == R.id.fab) {
            dialogShow();
        }else  if (id == R.id.conclusion) {
            if (day == null){
                //    save();
            }else {
                showEditDialog();
            }
        }
    }

    /**
     * 提交空 Day
     * 开启新的一天
     */
    private void pushNewDay(){

//        new Day().save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    toast("Day :" + objectId);
//                }else{
//                    toast("创建数据失败：" + e.getMessage());
//                }
//            }
//        });
    }


    /**
     * 保存计划内容到服务器
     *
     * @param p
     */
    private void savePlan(Plan p){
        presenter.addPlan(p);
//        OkHttpUtil.getDataByGET("http://192.168.0.103/SitUp/AddPlan?id=2&event=ceshishuju&startTime=2019-11-14%2011-30&endTime=2019-11-14%2011-35&quality=30", new MyStringCallback(){
//            @Override
//            public void onResponse(String response, int id) {
//                super.onResponse(response, id);
//                Toast.makeText(getActivity(), "添加数据成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                super.onError(call, e, id);
//                Log.d(TAG, e.getMessage() + " id -> " + id);
//                Toast.makeText(getActivity(), "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
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
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Plan plan = new Plan()
                        .setFrom(new String[]{fromHour.getText().toString(), fromMinutes.getText().toString()})
                        .setTo(new String[]{toHour.getText().toString(), toMinutes.getText().toString()})
                        .setName(planName.getText().toString());
                planList.add(plan);
                planAdapter.notifyDataSetChanged();
                savePlan(plan);
                dialog.dismiss();
            }
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
                    day.setConclusion(input);
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

    public List<Plan> getPlanList() {
        return planList;
    }

    @Override
    public void onSucceed(List<Plan> planList) {
        this.planList.clear();
        this.planList.addAll(planList);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSucceed(String succeedMessage) {
        toast(succeedMessage);
    }

    @Override
    public void onSucceed(Plan plan) {
        planList.remove(plan);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSucceed(Plan plan, int position) {
        planList.set(position, plan);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String errMessage) {
        new Exception("errMessage").printStackTrace();
        toast(errMessage);
    }

    @Override
    public UserBean getUserIfo() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        UserBean userBean = new UserBean();
        userBean.setId(UUID.fromString(preferences.getString("user_id", "")));
        return userBean;
    }
}
