package com.example.tome.module_shop_mall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.fagment.HomeFragment;
import com.example.tome.module_shop_mall.fagment.IMFragment;
import com.example.tome.module_shop_mall.fagment.MyCenterFragment;
import com.example.tome.module_shop_mall.fagment.NavigationV2Fragment;
import com.example.tome.module_shop_mall.helper.BottomNavigationViewHelper;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;
import com.example.tome.projectCore.base.mvc.BaseVcPermissionActivity;

import com.fec.core.router.arouter.RouterURLS;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@Route(path = RouterURLS.BASE_MAIN)
public class MainActivity extends BaseVcPermissionActivity implements View.OnClickListener {

    @BindView(R2.id.layout_pager)
    FrameLayout mFrameLayout;
    @BindView(R2.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R2.id.navigation_center_image)
    ImageView imageAdd;

    private static String TAG = "HomeActivity";
    private MenuItem menuItem;
    private MenuItem mItemWelfare;
    private MenuItem mItemVideo;
    private MenuItem mItemAboutUs;
    private MenuItem mItemLogout;
    private TextView mMUsTv;
    private List<Fragment> mFragmentList;
    private Fragment mCurrentFragment;
    private IMFragment imFragment;
    private ImageAlertDialogs selectImageDialog;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.navigation_center_image){
            startActivity(new Intent(MainActivity.this,PublishiGoodsActivity.class));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_home;
    }

    @Override
    protected void initTitle() {


    }

    @Override
    protected void initView() {
       /* mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.mainColor).init();
        StatuBarCompat.setImmersiveStatusBar(true,R.color.mainColor,this);*/
        imageAdd.setOnClickListener(this);
        selectImageDialog = new ImageAlertDialogs(this);
        //注册EventBus
        super.regEvent = true;
        initFragment();
        initBottomNavigationView();


    }


    private void initFragment() {
        mFragmentList = new ArrayList<>();
        imFragment = new IMFragment();
        mFragmentList.add(new HomeFragment(selectImageDialog));
        mFragmentList.add(imFragment);
        mFragmentList.add(new NavigationV2Fragment());
        mFragmentList.add(new MyCenterFragment());
        // mFragmentList.add(BaseHomeFragment.newInstance("我的"));
    }

    private void initBottomNavigationView() {

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        // 预设定进来后,默认显示fragment
        int getMyCenter = getIntent().getIntExtra("toMyCenter", 0);
        int getWebHome = getIntent().getIntExtra("homeWeb",0);
        if (getMyCenter == 4) {
            addFragment(R.id.layout_pager, mFragmentList.get(3));
            mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(4).getItemId());
        } else if (getWebHome == 1){
            addFragment(R.id.layout_pager, mFragmentList.get(0));
            mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(0).getItemId());
        } else{
            addFragment(R.id.layout_pager, mFragmentList.get(0));
        }

        Integer fromUserID = getIntent().getIntExtra("fromUserID",-1);
        if (fromUserID != -1){
            addFragment(R.id.layout_pager,mFragmentList.get(1));
            mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(3).getItemId());
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.tab_home) {
                    addFragment(R.id.layout_pager, mFragmentList.get(0));
                } else if (item.getItemId() == R.id.tab_find) {
                    RouterCenter.toGoodsClassify();
                    //addFragment(R.id.layout_pager, mFragmentList.get(1));
                } else if (item.getItemId() == R.id.tab_message) {
                    addFragment(R.id.layout_pager, mFragmentList.get(1));
                } else if (item.getItemId() == R.id.tab_self) {
                    addFragment(R.id.layout_pager, mFragmentList.get(3));
                }
                return true;
            }
        });

    }

    /**
     * 显示fragment
     *
     * @param frameLayoutId
     * @param fragment
     */
    private void addFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment).show(fragment);
                } else {
                    transaction.show(fragment);
                }
            } else {
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment).add(frameLayoutId, fragment);
                } else {
                    transaction.add(frameLayoutId, fragment);
                }
            }
            mCurrentFragment = fragment;
            transaction.commit();
        }
    }

    @Override
    public void getImageUrl(String url) {
        L.d("getImageUrl" + url);
       imFragment.sendImageUrl(url);
    }

}
