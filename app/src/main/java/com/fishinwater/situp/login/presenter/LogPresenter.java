package com.fishinwater.situp.login.presenter;

import com.fishinwater.situp.login.fragment.IBaseFragment;
import com.fishinwater.situp.login.model.IBaseLog;
import com.fishinwater.situp.login.model.IOnResultListener;
import com.fishinwater.situp.login.model.LogViewModel;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogPresenter extends BasePresenter<IBaseFragment> {

    private IBaseLog logViewModel;

    public LogPresenter(LogViewModel logViewModel) {
        this.logViewModel = logViewModel;
    }

    @Override
    public void login(String userName, String userPassword, IOnResultListener resultListener) {
        super.login(userName, userPassword, resultListener);
        logViewModel.login(userName, userPassword, resultListener);
    }
}
