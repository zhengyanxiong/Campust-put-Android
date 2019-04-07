package com.example.tome.module_shop_mall.activity;


import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.UltimateBar;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.fagment.HomeFragment;
import com.example.tome.module_shop_mall.fagment.KnowledgeSystemFragment;
import com.example.tome.module_shop_mall.fagment.MyCenterFragment;
import com.example.tome.module_shop_mall.fagment.NavigationV2Fragment;
import com.example.tome.module_shop_mall.fagment.ProjectFragment;
import com.example.tome.module_shop_mall.helper.BottomNavigationViewHelper;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;
import com.example.tome.projectCore.base.mvc.BaseVcPermissionActivity;

import com.fec.core.router.arouter.RouterURLS;

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
        StatuBarCompat.setImmersiveStatusBar(true, Color.WHITE, this);

    }

    @Override
    protected void initView() {
        imageAdd.setOnClickListener(this);
        StatuBarCompat.setImmersiveStatusBar(true, Color.WHITE, this);
        selectImageDialog = new ImageAlertDialogs(this);
        //注册EventBus
        super.regEvent = true;
        initFragment();
        initBottomNavigationView();


    }


    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment(selectImageDialog));
        mFragmentList.add(new KnowledgeSystemFragment());
        mFragmentList.add(new NavigationV2Fragment());
        mFragmentList.add(new MyCenterFragment());
        // mFragmentList.add(BaseHomeFragment.newInstance("我的"));
    }

    private void initBottomNavigationView() {

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        // 预设定进来后,默认显示fragment
        int getMyCenter = getIntent().getIntExtra("toMyCenter", 0);
        if (getMyCenter == 4) {
            addFragment(R.id.layout_pager, mFragmentList.get(3));
            mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(3).getItemId());
        } else {
            addFragment(R.id.layout_pager, mFragmentList.get(0));
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.tab_home) {
                    addFragment(R.id.layout_pager, mFragmentList.get(0));
                } else if (item.getItemId() == R.id.tab_find) {
                    addFragment(R.id.layout_pager, mFragmentList.get(1));
                } else if (item.getItemId() == R.id.tab_message) {
                    addFragment(R.id.layout_pager, mFragmentList.get(2));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (selectImageDialog != null && resultCode == RESULT_OK) {
            selectImageDialog.onActivityResult(requestCode, data);
        }
    }
}
