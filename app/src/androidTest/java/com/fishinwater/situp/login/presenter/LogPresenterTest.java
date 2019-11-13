package com.fishinwater.situp.login.presenter;

import android.util.Log;

import com.fishinwater.situp.login.model.IOnResultListener;
import com.fishinwater.situp.login.model.LogViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        logViewModel.login("abc", "123", new IOnResultListener() {
            @Override
            public void onSucceed() {
                Log.d(TAG, "onSucceed");
            }

            @Override
            public void onFailed(Exception error) {
                Log.d(TAG, "onFailed");
            }

            @Override
            public void onNameWrong() {
                Log.d(TAG, "onNameWrong");
            }

            @Override
            public void onPasswordWrong() {
                Log.d(TAG, "onPasswordWrong");
            }
        });
    }
}
