package com.feicuiedu.fancaicai.videonews.bompapi.result;

import com.feicuiedu.fancaicai.videonews.bompapi.entity.UserEntity;
import com.feicuiedu.fancaicai.videonews.bompapi.result.UserResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 用户相关网络接口
 */

public interface UserApi {
//    登录
    @GET("1/login")
    Call<UserResult> login(@Query("username") String uesrname, @Query("password") String password);
//注册
    @POST("1/users")
    Call <UserResult> register(@Body UserEntity userEntity);
}
