package com.fishinwater.base.callback;

import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.retrofit.UserIfoModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author fishinwater-1999
 * @version 2019-12-21
 */
public interface UserMgrService {

    /**
     * POST 用 Field
     */
    @POST("login")
    @FormUrlEncoded
    Call<UserBean> login(@Field("username") String username, @Field("password") String password);

    /**
     * GET 用 Query
     */
//    @GET("login")
//    Call<UserBean> login(@Query("username") String username, @Query("password") String password);

}
