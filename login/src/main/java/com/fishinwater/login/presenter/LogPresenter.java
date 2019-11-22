package com.fishinwater.login.presenter;

import com.fishinwater.login.fragment.IOnResultListener;
import com.fishinwater.login.model.IBaseLog;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogPresenter extends BasePresenter<IOnResultListener> {

    private IBaseLog logViewModel;

    public LogPresenter(IBaseLog logViewModel) {
        this.logViewModel = logViewModel;
    }

    @Override
    public void resister(String userName, String userPassword, IOnResultListener resultListener) {
        logViewModel.resist(userName, userPassword, resultListener);
    }

    @Override
    public void login(String userName, String userPassword, IOnResultListener resultListener) {
        logViewModel.login(userName, userPassword, resultListener);
    }

}
