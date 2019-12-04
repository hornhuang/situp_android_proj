package com.fishinwater.situp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.fishinwater.plan.fragment.BlankFragment;
import com.fishinwater.situp.R;
import com.fishinwater.situp.util.DataGeneratorUtil;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 *
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
//        ARouter.getInstance().build("/login/login")
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

    class MyTabSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            try {
                setSelectedFragment(tab.getPosition());
                for (int i = 0 ; i < mTabLayount.getTabCount() ; i++) {
                    View view = mTabLayount.getTabAt(i).getCustomView();
                    ImageView img  = view.findViewById(R.id.tab_content_image);
                    TextView title = view.findViewById(R.id.tab_context_title);
                    if (tab.getPosition() == i) {
                        Glide.with(MainActivity.this).load(DataGeneratorUtil.mTabPressed[i]).into(img);
                        title.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }else {
                        Glide.with(MainActivity.this).load(DataGeneratorUtil.mTabUnPressed[i]).into(img);
                        title.setTextColor(Color.GRAY);
                    }
                }
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

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
