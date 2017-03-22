package com.feicuiedu.fancaicai.videonews.ui.likes;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.fancaicai.videonews.R;
import com.feicuiedu.fancaicai.videonews.bompapi.entity.NewsEntity;
import com.feicuiedu.fancaicai.videonews.commons.CommonUtils;
import com.feicuiedu.fancaicai.videonews.commons.ToastUtils;
import com.feicuiedu.fancaicai.videonews.ui.base.BaseItemView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class LikesItemView extends BaseItemView<NewsEntity> {
    @BindView(R.id.ivPreview)
    ImageView ivPreview;
    @BindView(R.id.tvNewsTitle)
    TextView tvNewsTitle;
    @BindView(R.id.tvCreatedAt)
    TextView tvCreatedAt;
    private NewsEntity newsEntity;
    public LikesItemView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        LayoutInflater.from(getContext()).inflate(
                R.layout.item_likes,this,true);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindModel(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        tvNewsTitle.setText(newsEntity.getNewsTitle());
        tvCreatedAt.setText(CommonUtils.format(newsEntity.getCreatedAt()));
        //加载图片
        String url = CommonUtils.encodeUrl(newsEntity.getPreviewUrl());
        Picasso.with(getContext()).load(url).into(ivPreview);

    }
    @OnClick
    public void onClick(){
        //// TODO: 2017/3/22 0022 跳转到评论页面
        ToastUtils.showShort("跳转到评论页面");
    }
}
