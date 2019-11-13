package com.fishinwater.situp.login.presenter;

import android.util.Log;

import com.fishinwater.situp.login.fragment.IOnResultListener;
import com.fishinwater.situp.login.model.LogViewModel;
import com.zhy.http.okhttp.callback.StringCallback;

import org.junit.Before;
import org.junit.Test;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogPresenterTest {

    private final static String TAG = "LogPresenterTest";

    private LogViewModel logViewModel;

    @Before
    public void ini() {
        logViewModel = new LogViewModel();
    }

    @Test
    public void login() {

    }
}
