package com.fishinwater.postcenter.model.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.FavoriteBean;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.postcenter.model.PostModel;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

public class SquareViewModel extends BaseObservable {

    private Activity mActivity;

    PostModel postModel;

    public SquareViewModel(Activity activity) {
        this.mActivity = activity;
        this.postModel = new PostModel();
    }

    public void getAllPosts(int page, final IBaseCallback<PostBean> callback) {
        postModel.getAllPosts(page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.failed(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                List<String> list = JSONUtils.jsonStrtoList(String.class, response);
                if (list == null || list.size() == 0) {
                    callback.onSucceed(null);
                    return;
                }
                Consumer<String> consumer = new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        postModel.get(s, new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                callback.failed(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                callback.onSucceed(JSONUtils.StringToObj(PostBean.class, response));
                            }
                        });
                    }
                };
                Disposable disposable = Observable.fromIterable(list)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer);
            }
        });
    }

}
