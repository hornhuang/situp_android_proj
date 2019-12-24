package com.fishinwater.login.presenter;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.callback.IBaseRetCallback;
import com.fishinwater.base.callback.UserMgrService;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.ResponseUtil;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.login.ui.fragment.ILoginView;
import com.fishinwater.login.ui.fragment.IOnResultListener;
import com.fishinwater.login.model.IBaseLog;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogPresenter extends BasePresenter<ILoginView> {

    private IBaseLog logViewModel;

    public LogPresenter(IBaseLog logViewModel) {
        this.logViewModel = logViewModel;
    }

    @Override
    public void resister(String userName, String userPassword, final ILoginView iLoginView) {
        logViewModel.resist(userName, userPassword, new IBaseRetCallback<String>() {
            @Override
            public void onSucceed(Response<String> response) {
                if (response.body().contains(ResponseUtil.WRONG_NAME)){
                    iLoginView.showLoginFailed(ILoginView.ErrCode.WRONG_USER_NAME);
                } else if (response.body().contains(ResponseUtil.SUCCEED)) {
                    iLoginView.showLoginSuccess(response.body());
                } else {
                    iLoginView.showLoginFailed(ILoginView.ErrCode.WRONG_NET_WORK);
                }
            }

            @Override
            public void onFailed(Throwable t) {
                iLoginView.showLoginFailed(ILoginView.ErrCode.WRONG_NET_WORK);
            }
        });

    }

    @Override
    public void login(String userName, String userPassword, final ILoginView iLoginView) {
        logViewModel.login(userName, userPassword, new IBaseRetCallback<UserBean>() {
            @Override
            public void onSucceed(Response<UserBean> response) {
                UserBean userBean = response.body();
                if (userBean != null) {
                    String user_id = userBean.getUser_id();
                    iLoginView.showLoginSuccess(user_id);
                }
            }

            @Override
            public void onFailed(Throwable t) {
                iLoginView.showLoginFailed(ILoginView.ErrCode.WRONG_NET_WORK);
            }
        });

    }
}
