package com.fishinwater.situp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.fishinwater.plan.fragment.Fragment.PlanFragment;
import com.fishinwater.situp.R;
import com.fishinwater.situp.ui.fragment.ChartFragment;
import com.fishinwater.situp.ui.fragment.MineFragment;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public class DataGeneratorUtil {

    public static int[] mTabUnPressed = {R.drawable.plan_ordinary, R.drawable.square_ordinary, R.drawable.situation_ordinary, R.drawable.home_ordinary};

    public static int[] mTabPressed   = {R.drawable.plan_press, R.drawable.square_press, R.drawable.situation_press, R.drawable.home_press};

    public static int[] mtabTitles = {R.string.main_plan, R.string.main_playground, R.string.main_chart, R.string.main_user };

    public static Fragment[] getFragments(String from) {
        Fragment[] fragments = new Fragment[4];
        fragments[0] = PlanFragment.newInstance(from);
        fragments[1] = ChartFragment.newInstance(from);
        fragments[2] = ChartFragment.newInstance(from);
        fragments[3] = MineFragment.newInstance(from);
        return fragments;
    }

    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_tab_layout, null);
        ImageView img = view.findViewById(R.id.tab_content_image);
        TextView text = view.findViewById(R.id.tab_context_title);
        Glide.with(context).load(mTabUnPressed[position]).into(img);
        text.setText(mtabTitles[position]);
        return view;
    }

}
