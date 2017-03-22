package com.feicuiedu.fancaicai.videonews.ui.news;

import android.content.Context;
import android.util.AttributeSet;

import com.feicuiedu.fancaicai.videonews.bompapi.entity.NewsEntity;
import com.feicuiedu.fancaicai.videonews.bompapi.result.QueryResult;
import com.feicuiedu.fancaicai.videonews.ui.base.BaseResourceView;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class NewsListView extends BaseResourceView<NewsEntity,NewsItemView>{
    public NewsListView(Context context) {
        super(context);
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {

        return mNewsApi.getVideoNewsList(limit,skip);
    }

    @Override
    protected int getLimit() {
        return 5;
    }

    @Override
    protected NewsItemView createItemView() {

        return new NewsItemView(getContext());
    }
}
