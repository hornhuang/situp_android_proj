package com.fishinwater.login.ui.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseActivity {

    /**
     * 切换当前碎片
     * @param fragment
     */
    void replaceFragment(@NonNull Fragment fragment);

}
