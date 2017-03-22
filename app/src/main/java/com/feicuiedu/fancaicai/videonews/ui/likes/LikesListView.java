package com.feicuiedu.fancaicai.videonews.ui.likes;

import android.content.Context;
import android.util.AttributeSet;

import com.feicuiedu.fancaicai.videonews.UserManager;
import com.feicuiedu.fancaicai.videonews.bompapi.entity.NewsEntity;
import com.feicuiedu.fancaicai.videonews.bompapi.other.InQuery;
import com.feicuiedu.fancaicai.videonews.bompapi.result.BombConst;
import com.feicuiedu.fancaicai.videonews.bompapi.result.QueryResult;
import com.feicuiedu.fancaicai.videonews.ui.base.BaseItemView;
import com.feicuiedu.fancaicai.videonews.ui.base.BaseResourceView;

import retrofit2.Call;

/**
 * 我的收藏类表视图
 */


public class LikesListView extends BaseResourceView<NewsEntity,LikesItemView> {
    public LikesListView(Context context) {
        super(context);
    }

    public LikesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LikesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //请求数据（创建一个请求）
    @Override
    protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {
        String userId = UserManager.getInstance().getObjectId();
        //由于服务器原因造成的参数（可以直接使用）
        InQuery where = new InQuery(BombConst.FIELD_LIKES,BombConst.TABLE_USER,userId);
        return mNewsApi.getLikedList(limit,skip,where);
    }
    //每次请求多少条数据
    @Override
    protected int getLimit() {
        return 15;
    }
    //实例化ItemView
    @Override
    protected LikesItemView createItemView() {
        LikesItemView likesItemView = new LikesItemView(getContext());
        return likesItemView;
    }

    //退出登录时，清空收藏列表
    //退出登录时，清空收藏列表
    public void clear(){
        mModelAdapter.clear();
    }
}
