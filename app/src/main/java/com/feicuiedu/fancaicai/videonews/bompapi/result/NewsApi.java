package com.feicuiedu.fancaicai.videonews.bompapi.result;

import com.feicuiedu.fancaicai.videonews.bompapi.entity.NewsEntity;
import com.feicuiedu.fancaicai.videonews.bompapi.other.InQuery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * 新闻相关网络接口
 */

public interface NewsApi {

    //获取新闻列表,排序方式，接时间新到旧排序
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getVideoNewsList(
            @Query("limit") int limit,
            @Query("skip") int skip);


    //获取收藏列表
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getLikedList(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where
    );

}

