package com.fishinwater.situp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.fishinwater.situp.R;
import com.fishinwater.situp.util.DataGeneratorUtil;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        iniView();
//        ARouter.getInstance().build("/UserCenter/login")
//                .withLong("key1", 666L)
//                .withString("key3", "888")
//                .navigation();
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
