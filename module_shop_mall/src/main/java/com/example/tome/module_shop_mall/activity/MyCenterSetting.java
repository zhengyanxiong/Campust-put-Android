package com.example.tome.module_shop_mall.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.fagment.MyCenterFragment;
import com.fec.core.router.arouter.RouterURLS;

import butterknife.BindView;

/**
 * Author: created by Bernie on 2019/3/26
 **/
@Route(path = RouterURLS.MYCENTER_SETTING)
public class MyCenterSetting extends BaseVcActivity implements View.OnClickListener {
    @BindView(R2.id.common_toolbar_title)
    TextView mTitle;
    @BindView(R2.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.btn_login_out)
    Button loginOut;

    AlertDialog builder;

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_mycenter_setting;
    }

    @Override
    protected void initTitle() {
        mTitle.setText("设置");
    }

    @Override
    protected void initView() {
        mToolbar.setOnClickListener(this);
        loginOut.setOnClickListener(this);
        String memberToken = (String)SPUtil.get(this,Constants.MEMBER_TOCKEN,"");
        if(null == memberToken || "".equals(memberToken)) {
            loginOut.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.common_toolbar) {
            onBackPressed();
        } else if (i == R.id.btn_login_out) {

            new AlertDialog.Builder(this)
                    .setMessage("确定要注销当前账户吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SPUtil.remove(getContext(), Constants.MEMBER_TOCKEN);
                            Intent intent = new Intent(MyCenterSetting.this, MainActivity.class);
                            intent.putExtra("toMyCenter", 4);
                            startActivity(intent);
                            ToastUtils.showCenter("注销成功！");
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    }).create().show();


        }
    }
}
