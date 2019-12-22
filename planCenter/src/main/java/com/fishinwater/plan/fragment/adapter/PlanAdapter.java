package com.fishinwater.plan.fragment.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.fishinwater.base.data.protocol.PlanBean;
import com.fishinwater.plan.R;
import com.fishinwater.plan.fragment.Fragment.PlanFragment;
import com.fishinwater.base.PopupWindowUtil;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private int x, y;

    private PopupWindow mPopupWindow;

    private final int PASSLINE = 80;

    private List<PlanBean> mList;

    private Activity context;

    private PlanFragment fragment;

    public PlanAdapter(List<PlanBean> mList, Activity context, PlanFragment fragment){
        this.mList    = mList;
        this.context  = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_plan, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final PlanBean plan = mList.get(i);
        if (plan.getPlan_start_date() != null) {
            viewHolder.from.setText(plan.getPlan_start_date()+ " : " + plan.getPlan_start_date());
        }
        if (plan.getPlan_end_date()   != null) {
            viewHolder.to.setText(plan.getPlan_end_date() + " : " + plan.getPlan_end_date());
        }
        viewHolder.name.setText(plan.getPlan_title());
        final ImageView img = viewHolder.degree;
        setImg(img, plan);
        viewHolder.itemView.setOnClickListener(v -> showProgressDialog(img, plan, i));
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    return true;
                }
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });
        final View finalConvertView = viewHolder.itemView;
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupWindow(finalConvertView, plan);
                return false;
            }
        });
    }

    /**
     * 自定义布局
     * 由于设定计划完成进度
     */
    private void showProgressDialog(final ImageView img, final PlanBean plan, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_reset_degree, null);
        final SeekBar mseekbar  = v.findViewById(R.id.seekbar);
        mseekbar.setProgress(Integer.parseInt(plan.getPlan_score()));
        Button btn_sure   = v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = v.findViewById(R.id.dialog_btn_cancel);
        // builer.setView(v);// 这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        //自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.getWindow().setContentView(v);
        // dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plan.setPlan_score(mseekbar.getProgress() + "");
                fragment.getPresenter().updatePlan(plan, position);
                setImg(img, plan);
                dialog.dismiss();
                mList.add(plan);
                notifyDataSetChanged();
            }
        });

        btn_cancel.setOnClickListener((View arg0) -> {
                dialog.dismiss();
            }
        );
    }

    /**
     * 弹出对话框
     * @param anchorView
     * @param plan
     */
    private void showPopupWindow(View anchorView, PlanBean plan) {
        View contentView = getPopupWindowContentView(plan);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView, x, y);
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    /**
     * 初始化对话框布局
     * @param plan
     * @return
     */
    private View getPopupWindowContentView(final PlanBean plan) {
        // 一个自定义的布局，作为显示的内容
        // 布局ID
        int layoutId = R.layout.popup_content_layout;
        View contentView = LayoutInflater.from(context).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.menu_item1) {
                    showDeleteDialog(plan);
                }

                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        return contentView;
    }

    /**
     * @setIcon 设置对话框图标
     * @setTitle 设置对话框标题
     * @setMessage 设置对话框消息提示
     * setXXX方法返回Dialog对象，因此可以链式设置属性
     */
    private void showDeleteDialog(final PlanBean plan){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.write_pen);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("你确定要删除本计划?");
        normalDialog.setPositiveButton("确定",
                (dialog, which) -> {
                    fragment.getPresenter().deletePlan(plan);
                });
        normalDialog.setNegativeButton("关闭",
                (dialog, which) -> {
                    //...To-do
                });
        // 显示
        normalDialog.show();
    }

    /**
     *
     * @param Img
     * @param plan
     */
    private void setImg(ImageView Img, PlanBean plan) {
        if (Integer.parseInt(plan.getPlan_score()) != 0){
            int degree = Integer.parseInt(plan.getPlan_score());
            if ( degree > PASSLINE || degree == PASSLINE ){
                Img.setImageResource(R.drawable.completed);
            }else{
                Img.setImageResource(R.drawable.failed);
            }
        }else {
            Img.setImageResource(R.drawable.undo);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView degree;

        private TextView from;

        private TextView to;

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            degree   = itemView.findViewById(R.id.degree);
            name     = itemView.findViewById(R.id.name);
            from     = itemView.findViewById(R.id.from);
            to       = itemView.findViewById(R.id.to);

        }
    }
}
