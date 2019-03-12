package com.example.tome.module_shop_mall.fagment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.presenter.MyCenterPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public class MyCenterFragment extends BaseVpFragment<MyCenterContract.View,MyCenterContract.Presenter> implements MyCenterContract.View{
    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_right_text)
    TextView mTitleRightText;
    @BindView(R2.id.title_right_icon)
    ImageView mTitleRightIcon;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;
    @BindView(R2.id.rl_title_bar_content)
    RelativeLayout mRlTitleBarContent;

    @Override
    public MyCenterContract.Presenter createPresenter() {
        return new MyCenterPresenter();
    }

    @Override
    public MyCenterContract.View createView() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_mycenter;
    }

    @Override
    protected void initTitle() {
        mTitleContentText.setText("个人中心");
    }

    @Override
    protected void initView() {
        mPresenter.getMyCenterClassifyData();
    }

    @Override
    public void showMyCenterClassifyData(List<ProjectClassifyBean> projectClassifyDataList) {
        L.d("this is showMyCenterClassifyData");
    }
}
