package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.net.Uri;
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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
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
import com.example.tome.module_shop_mall.activity.MyGoodsPublshActivity;
import com.example.tome.module_shop_mall.activity.UserHomeActivity;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.bean.UserAllInfor;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.presenter.MyCenterPresenter;
import com.fec.core.router.arouter.RouterURLS;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import retrofit2.http.Path;

/**
 * Author: created by Bernie on 2019/3/12
 **/
@Route(path = RouterURLS.MALL_MYCENTER)
public class MyCenterFragment extends BaseVpFragment<MyCenterContract.View, MyCenterContract.Presenter> implements MyCenterContract.View {

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
    @BindView(R2.id.img_avatar)
    ImageView userHead;
    @BindView(R2.id.mycenter_num_publish)
    TextView myPublish;
    @BindView(R2.id.mycenter_num_sell)
    TextView mySellNum;
    @BindView(R2.id.mycenter_num_buy)
    TextView myBuyNum;
    @BindView(R2.id.mycenter_num_collection)
    TextView myBuyCar;
    private static String userToken = "";


    @Override
    public MyCenterContract.Presenter createPresenter() {
        return new MyCenterPresenter();
    }

    @Override
    public MyCenterContract.View createView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        setHasOptionsMenu(true);


        mImmersionBar.fitsSystemWindows(true).statusBarColor("#F4942300").init();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((AppCompatActivity) getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        myPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), MyGoodsPublshActivity.class));
                }
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
        StatuBarCompat.setTranslucentStatus(getActivity());
        //判断用户是否登录
        String memberToken = (String) SPUtil.get((AppCompatActivity) getActivity(), Constants.MEMBER_TOCKEN, "");
        userToken = memberToken;
        RelativeLayout.LayoutParams rl_user = (RelativeLayout.LayoutParams) userImg.getLayoutParams();
        if (null == memberToken || "".equals(memberToken)) {
            ToastUtils.showCenter("用户未登录");
            rl_user.setMargins(0, 26, 0, 0);
            rl_user.addRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            showUserInfor.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
            myPublish.setText("-");
            mySellNum.setText("-");
            myBuyNum.setText("-");
            myBuyCar.setText("-");
        } else {
            mPresenter.getUserInfor(memberToken);

            ToastUtils.showCenter(memberToken);
            rl_user.setMargins(25, 56, 0, 0);
            rl_user.removeRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            startButton.setVisibility(View.GONE);
            showUserInfor.setVisibility(View.VISIBLE);

            userHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), UserHomeActivity.class));
                }
            });
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
            RouterCenter.toMyCenterSetting();
        }
        return true;
    }

    @Override
    public void getUserInfor(UserInfor userInfor) {
        username.setText(userInfor.getUsername());
        userEmail.setText(userInfor.getEmail());
        Glide.with(this)
                .load(userInfor.getHeadImag())
                .into(userHead);
        //userHead.setImageURI(Uri.parse(userInfor.getHeadImag()));
        mPresenter.getUserGoodsInfor(userInfor.getUserId());
        //ToastUtils.showLong(getActivity(), userInfor.toString());
    }

    @Override
    public void getUserGoodsInfor(UserAllInfor jsonObject) {

        L.d("getUserGoodsInfor--JSONObject--publishClassCount-" + jsonObject.getPublishClassCount());
        myPublish.setText(jsonObject.getPublishClassCount()+"");
        mySellNum.setText(jsonObject.getUserSellCount()+"");
        myBuyNum.setText(jsonObject.getUserBuyCount()+"");

    }
}
