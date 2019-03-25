package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.core.util.UltimateBar;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.LoginActivity;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.presenter.MyCenterPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public class MyCenterFragment extends BaseVpFragment<MyCenterContract.View, MyCenterContract.Presenter> implements MyCenterContract.View{

    /*@BindView(R2.id.title_my_center_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_my_center_right_icon)
    ImageView mTitleRightIcon;*/
    @BindView(R2.id.mycenter_toolbar)
    Toolbar toolbar;
    @BindView(R2.id.btn_start_login)
    Button startButton;
    @BindView(R2.id.title_user_img)
    RelativeLayout userImg;
    @BindView(R2.id.showUserInfor)
    RelativeLayout showUserInfor;
    @BindView(R2.id.tv_name)
    TextView username;
    @BindView(R2.id.tv_email)
    TextView userEmail;

    @Override
    public MyCenterContract.Presenter createPresenter() {
        return new MyCenterPresenter();
    }

    @Override
    public MyCenterContract.View createView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        setHasOptionsMenu(true);

        /*mImmersionBar.fitsSystemWindows(true).statusBarColor("#8cd728")
                .statusBarDarkFont(true, 0.1f).init();*/
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((AppCompatActivity)getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_mycenter;
    }

    @Override
    protected void initTitle() {
        /*mTitleContentText.setText("个人中心");
        mTitleRightIcon.setImageResource(R.drawable.icon_settings);
        mTitleRightIcon.setVisibility(View.VISIBLE);*/
    }

    @Override
    protected void initView() {
        //判断用户是否登录
        String memberToken = (String)SPUtil.get((AppCompatActivity)getActivity(),Constants.MEMBER_TOCKEN,"");
        RelativeLayout.LayoutParams rl_user =(RelativeLayout.LayoutParams) userImg.getLayoutParams();
        if(null == memberToken || "".equals(memberToken)){
            ToastUtils.showCenter("用户未登录");
            rl_user.setMargins(0,26,0,0);
            rl_user.addRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            showUserInfor.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
        }else {
            mPresenter.getUserInfor(memberToken);
            ToastUtils.showCenter(memberToken);
            rl_user.setMargins(25,56,0,0);
            rl_user.removeRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            startButton.setVisibility(View.GONE);
            showUserInfor.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        L.d("onCreateOptionsMenu()");
        menu.clear();
        inflater.inflate(R.menu.mall_mycenter_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_settings == item.getItemId()) {
            ToastUtils.showCenter("你点了设置按钮。。。");
        }
        return true;
    }

    @Override
    public void getUserInfor(UserInfor userInfor) {
        username.setText(userInfor.getUsername());
        userEmail.setText(userInfor.getEmail());
        ToastUtils.showLong(getActivity(),userInfor.toString());
    }
}
