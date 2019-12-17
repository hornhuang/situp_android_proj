package com.fishinwater.situp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.SharedPreferencesUtil;
import com.fishinwater.base.model.DayModel;
import com.fishinwater.situp.R;
import com.fishinwater.situp.util.DataGeneratorUtil;
import com.google.android.material.tabs.TabLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 主页
 * @author fishinwater-1999
 */
public class MainActivity extends BaseActivity {

    private Fragment[] fragments;

    @BindView(R.id.bottom_tab_layout)
    public TabLayout mTabLayount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniDay();
        iniUser();
        iniView();
        ARouter.getInstance().build(RouteUtils.LogActivity)
                .navigation();
    }

    private void iniUser() {
        if ("".equals(SharedPreferencesUtil.getString(this, SharedPreferencesUtil.USER_KEY))) {
            SharedPreferencesUtil.putString(this, SharedPreferencesUtil.USER_KEY, "445417a0-560c-40c9-aeeb-bab98f501be1");
        }
    }

    private void iniDay() {
        if (!DateUtils.getDayDateStr()
                .equals(SharedPreferencesUtil.getString(this, SharedPreferencesUtil.CURRENT_DAY))) {
            new DayModel().publishDay(
                    SharedPreferencesUtil.getString(this, SharedPreferencesUtil.USER_KEY),this);
        }

    }

    private void iniView() {
        fragments   = DataGeneratorUtil.getFragments("your data to fragment");
        mTabLayount.addOnTabSelectedListener(new MyTabSelectedListener());
        for(int i = 0 ; i < fragments.length ; i ++) {
            mTabLayount.addTab(mTabLayount.newTab().setCustomView(DataGeneratorUtil.getTabView(this, i)));
        }
    }

    enum TabStatus {
        /**
         * 选中
         */
        SELECTED,
        /**
         * 未选中
         */
        UNSELECT
    }

    class MyTabSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            setTabColor(tab, TabStatus.SELECTED);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            setTabColor(tab, TabStatus.UNSELECT);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            Log.d(TAG, "onTabReselected----" + tab.getPosition());
            // just like double click
            // you can do nothing or refresh here
        }

        private void setTabColor(TabLayout.Tab tab, TabStatus status) {
            int position = tab.getPosition();
            View view = mTabLayount.getTabAt(tab.getPosition()).getCustomView();
            assert view != null;
            ImageView img  = view.findViewById(R.id.tab_content_image);
            TextView title = view.findViewById(R.id.tab_context_title);
            switch (status) {
                case SELECTED:
                    title.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Glide.with(MainActivity.this).load(DataGeneratorUtil.mTabPressed[position]).into(img);
                    setSelectedFragment(position);
                    break;
                case UNSELECT:
                    Glide.with(MainActivity.this).load(DataGeneratorUtil.mTabUnPressed[position]).into(img);
                    title.setTextColor(Color.GRAY);
                    break;
                default:
                    break;
            }


        }

        private void setSelectedFragment(int position) {
            if (position < fragments.length) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_container, fragments[position]).commit();
            }
        }
    }



}
