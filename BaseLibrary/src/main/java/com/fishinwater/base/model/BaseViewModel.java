package com.fishinwater.base.model;

import android.app.Application;

import androidx.databinding.BaseObservable;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public abstract class BaseViewModel extends BaseObservable {

    private Application application;

    public BaseViewModel(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
