package com.feicuiedu.fancaicai.videonews.bompapi;

import com.feicuiedu.fancaicai.videonews.bompapi.result.NewsApi;
import com.feicuiedu.fancaicai.videonews.bompapi.result.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class BombClient {
   private UserApi mUserApi;
    private NewsApi mNewsApi;
    private Retrofit mRetrofit;
    private static BombClient mBombClient;
    private OkHttpClient mOkHttpClient;

    public static BombClient getInstance() {
        if (mBombClient == null) {
            mBombClient = new BombClient();
        }
        return mBombClient;
    }

    private BombClient() {
        //构建“日志拦截器”
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置“日志拦截器”的拦截级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                //添加Bomb必要的头字段的拦截器
                .addInterceptor(new BombIntercepted())
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();
        //让gson能够将bomb返回的时间戳自动转换为Date对象
        Gson gson=new GsonBuilder()
                .setDateFormat("yyyy-mm-dd HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)//目的是使用okhttpclient身上的拦截器
                .baseUrl("https://api.bmob.cn/")
                //添加转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    //拿到UserApi
public UserApi getUserApi(){
    if (mUserApi==null){
        mUserApi= mRetrofit.create(UserApi.class);
    }
    return mUserApi;
}
    //拿到NewsApi
    public NewsApi getmNewsApi(){
        if (mNewsApi==null){
            mNewsApi= mRetrofit.create(NewsApi.class);
        }
        return mNewsApi;
    }

}
