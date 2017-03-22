package com.feicuiedu.fancaicai.videonews.ui.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.feicuiedu.fancaicai.videonews.R;
import com.feicuiedu.fancaicai.videonews.UserManager;
import com.feicuiedu.fancaicai.videonews.commons.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LikesFragment extends Fragment implements LoginFragment.OnLoginSuccessListener,RegisterFragment.OnRegisterSuccessListener {

    @BindView(R.id.tvUsername)
    TextView mTvUsername;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.btnLogout)
    Button mBtnLogout;
    @BindView(R.id.divider)
    View mDivider;
    @BindView(R.id.likesListView)
    LikesListView likesListView;
    private View view;

    private LoginFragment loginfragment;
    private RegisterFragment registerfragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_likes,container,false);
            ButterKnife.bind(this,view);
//判断用户登录信息，如果已登录，则自动登录
            //判断用户登录状态，更新UI
            UserManager userManager = UserManager.getInstance();
            if (!userManager.isOffline()){
                userOnLine(userManager.getUsername(),userManager.getObjectId());
            }
        }
        return view;
    }

    @OnClick({R.id.btnRegister,R.id.btnLogin,R.id.btnLogout})
    public void onClick(View view){
        switch (view.getId()){
            //注册
            case R.id.btnRegister:
                if (registerfragment == null){
                    registerfragment = new RegisterFragment();
                    //添加注册成功的监听
                    registerfragment.setListener(this);
                }
                registerfragment.show(getChildFragmentManager(),"Register Dialog");
                break;
            //登录
            case R.id.btnLogin:
                if (loginfragment == null){
                    loginfragment = new LoginFragment();
//                    添加登录成功的监听
                    loginfragment.setListener(this);
                }
                loginfragment.show(getChildFragmentManager(),"Login Dialog");
                break;
            //退出登录
            case R.id.btnLogout:
                //用户下线
                userOffline();
                ToastUtils.showShort("用户已退出登录");
                break;
        }
    }

    @Override
    public void loginSuccess(String username, String objectId) {
        loginfragment.dismiss();
        //用户上线
        userOnLine(username,objectId);
    }

    @Override
    public void registerSuccess(String username, String objectId) {
        //关闭注册的对话框
        registerfragment.dismiss();
        //用户上线
        userOnLine(username,objectId);
    }
    //用户上线
    private void userOnLine(String username,String objectId){
        //更新UI
        mBtnLogin.setVisibility(View.INVISIBLE);
        mBtnRegister.setVisibility(View.INVISIBLE);
        mBtnLogout.setVisibility(View.VISIBLE);
        mDivider.setVisibility(View.INVISIBLE);
        mTvUsername.setText(username);
        // 存储用户信息
        UserManager.getInstance().setUsername(username);
        UserManager.getInstance().setObjectId(objectId);
        // 刷新收藏列表
        //刷新收藏列表
        likesListView.autoRefresh();
    }
    //用户下线
    private void userOffline(){
        //清除用户相关信息
        UserManager.getInstance().clear();
        //更新UI
        mBtnLogin.setVisibility(View.VISIBLE);
        mBtnRegister.setVisibility(View.VISIBLE);
        mBtnLogout.setVisibility(View.INVISIBLE);
        mDivider.setVisibility(View.VISIBLE);
        mTvUsername.setText(R.string.tourist);
        //清空收藏列表
        likesListView.clear();
    }
}
