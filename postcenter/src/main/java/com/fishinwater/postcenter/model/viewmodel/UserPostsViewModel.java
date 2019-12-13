package com.fishinwater.postcenter.model.viewmodel;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.fishinwater.base.callback.MyObjCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.FavoriteBean;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.postcenter.model.PostModel;
import com.fishinwater.postcenter.model.UserPostsModel;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class UserPostsViewModel extends PostBean {

    private PostModel postModel;

    private UserPostsModel model;

    private Activity mContext;

    public UserPostsViewModel(Activity mContext) {
        this.model = new UserPostsModel();
        this.postModel = new PostModel();
        this.mContext = mContext;
    }

    public void getUserPosts(String user_id, final MyObjCallback<PostBean> callback) {
        model.getUserPosts(user_id,
                new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Consumer<String> consumer = new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                postModel.get(s, new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        callback.onFailed(e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        callback.onSucceed(JSONUtils.StringToObj(PostBean.class, response));
                                    }
                                });
                            }
                        };
                        Disposable disposable = Observable.fromIterable(JSONUtils.jsonStrtoList(String.class, response))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(consumer);
                    }

                });
    }

    public void getUserFavoritePosts(String user_id, final MyObjCallback<PostBean> callback) {
        model.getUserFavorites(user_id,
                new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                        Consumer<String> consumer = new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                postModel.get(s, new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        callback.onFailed(e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        callback.onSucceed(JSONUtils.StringToObj(PostBean.class, response));
                                    }
                                });
                            }
                        };
                        Disposable disposable = Observable.fromIterable(JSONUtils.jsonStrtoList(FavoriteBean.class, response))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map(new Function<FavoriteBean, String>() {
                                    @Override
                                    public String apply(FavoriteBean favoriteBean) throws Exception {
                                        return favoriteBean.getPost_id();
                                    }
                                })
                                .subscribe(consumer);
                    }

                });
    }
}
