package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.base.mvp.BaseVpActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.StringUtils;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_cart.utils.BasicTool;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.bean.LoginBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.ILoginsContract;
import com.example.tome.module_shop_mall.presenter.LoginsPresenter;
import com.example.tome.module_shop_mall.widget.EditTextWithDel;
import com.facebook.stetho.common.StringUtil;

import butterknife.BindView;

/**
 * Author: created by Bernie on 2019/3/22
 **/
public class LoginActivity extends BaseVpActivity<ILoginsContract.View,ILoginsContract.Presenter> implements ILoginsContract.View {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.etd_tel)
    EditTextWithDel mEtdTel;
    @BindView(R2.id.etd_pwd)
    EditTextWithDel mEtdPwd;
    @BindView(R2.id.cb_show_hide_pwd)
    CheckBox mCbShowHidePwd;
    @BindView(R2.id.btn_login)
    Button mBtnLogin;
    @BindView(R2.id.tv_service_phone_remark)
    TextView mTvServicePhoneRemark;
    @BindView(R2.id.layout_login)
    LinearLayout mLayoutLogin;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_login;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public ILoginsContract.Presenter createPresenter() {
        return new LoginsPresenter(this);
    }

    @Override
    public ILoginsContract.View createView() {
        return this;
    }

    @Override
    public void initView() {

        setImmeriveStatuBar();
        //StatuBarCompat.setTranslucentStatus(this);
        //登录
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPage()){
                    LoginBean loginBean = new LoginBean();
                    loginBean.setUserName(mEtdTel.getText().toString());
                    loginBean.setPassword(mEtdPwd.getText().toString());
                    mPresenter.login(loginBean);
                }
            }
        });

        //显示隐藏密码
        mCbShowHidePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCbShowHidePwd.isChecked()) {
                    mEtdPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    mEtdPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    /**
     * 校验
     * @return
     */
    private boolean checkPage() {
        if (BasicTool.isEmpty(mEtdTel.getText())) {
            ToastUtils.showCenter(mEtdTel.getHint().toString());
            return false;
        } else if (BasicTool.isEmpty(mEtdTel.getText())) {
            ToastUtils.showCenter(mEtdTel.getHint().toString());
            return false;
        }
        return true;
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showCenter("登录成功");
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("toMyCenter",4);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        ToastUtils.showCenter("登录失败，用户名或密码错误");
    }

}
