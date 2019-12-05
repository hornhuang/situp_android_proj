package com.fishinwater.plan.fragment.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fishinwater.plan.R;
import com.fishinwater.plan.classes.base.Plan;
import com.fishinwater.util.PopupWindowUtil;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private String TAG = "PlanAdapter";

    private int x, y;

    private PopupWindow mPopupWindow;

    private final int PASSLINE = 80;

    private List<Plan> mList;

    private Activity context;

    private Fragment fragment;

    public PlanAdapter(List<Plan> mList, Activity context, Fragment fragment){
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
        final Plan plan = mList.get(i);
        if (plan.getFrom() != null) {
            viewHolder.from.setText(plan.getFrom()[0] + " : " + plan.getFrom()[1]);
        }
        if (plan.getTo()   != null) {
            viewHolder.to.setText(plan.getTo()[0] + " : " + plan.getTo()[1]);
        }
        viewHolder.name.setText(plan.getName());
        final ImageView img = viewHolder.degree;
        setImg(img, plan);
        viewHolder.itemView.setOnClickListener(v -> showProgressDialog(img, plan));
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
                Log.d(TAG, "onLongClick");
                showPopupWindow(finalConvertView, plan);
                return false;
            }
        });
    }

    /**
     * 自定义布局
     * 由于设定计划完成进度
     */
    private void showProgressDialog(final ImageView img, final Plan plan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_reset_degree, null);
        final SeekBar mseekbar  = v.findViewById(R.id.seekbar);
        mseekbar.setProgress(plan.getDegree());
        Button btn_sure   = v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = v.findViewById(R.id.dialog_btn_cancel);
        // builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        // dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                plan.setDegree(mseekbar.getProgress());
                plan.setCompleted(true);
                // ((PlanFragment)fragment).updatePlan(plan);
                setImg(img, plan);
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    private void showPopupWindow(View anchorView, Plan plan) {
        View contentView = getPopupWindowContentView(plan);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView, x, y);
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    private View getPopupWindowContentView(final Plan plan) {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.popup_content_layout;   // 布局ID
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
    private void showDeleteDialog(final Plan plan){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.write_pen);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("你确定要删除本计划?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePlan(plan);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void  deletePlan(final Plan plan){
//        plan.delete(new UpdateListener() {
//
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show();
//                    ((PlanFragment) fragment).getPlanList().remove(plan);
//                    ((PlanFragment) fragment).upDate();
//                    notifyDataSetChanged();
//                }else{
//
//                }
//            }
//
//        });
    }

    private void setImg(ImageView Img, Plan plan) {
        Log.d(TAG, plan.isCompleted()+"");
        if (plan.isCompleted()){
            int degree = plan.getDegree();
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
