package com.example.tome.module_shop_mall.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.EventBusBean;
import com.example.tome.core.base.BaseEventbusBean;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.fagment.HomeFragment;
import com.example.tome.module_shop_mall.fagment.KnowledgeSystemFragment;
import com.example.tome.module_shop_mall.fagment.NavigationV2Fragment;
import com.example.tome.module_shop_mall.fagment.ProjectFragment;
import com.example.tome.module_shop_mall.helper.BottomNavigationViewHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/5/17 18:20
 * @描述 ${主页的tab}
 */

@Route(path = RouterURLS.SHOP_MALL_HOME)
public class HomeActivity extends BaseVcActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R2.id.layout_pager)
    FrameLayout mFrameLayout;
    @BindView(R2.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private static String TAG = "HomeActivity";
    private MenuItem menuItem;
    private MenuItem mItemWelfare;
    private MenuItem mItemVideo;
    private MenuItem mItemAboutUs;
    private MenuItem mItemLogout;
    private TextView mMUsTv;
    private List<Fragment> mFragmentList;
    private Fragment mCurrentFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_home;
    }

    @Override
    protected void initTitle() {
      //  mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
       // mImmersionBar.statusBarView(R.id.view_bar).init();
        //取代原本的ActionBar
       // setSupportActionBar(mToolbar);
       // ActionBar actionBar = getSupportActionBar();
       // assert actionBar != null;
        //actionBar.setDisplayShowTitleEnabled(false);
        //mToolbarTitle.setText("首页");

        //因为setSupportActionBar里面也会setNavigationOnClickListener
       // mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {

      //      }
     //   });
    }

    @Override
    protected void initView() {
        //注册EventBus
        super.regEvent = true ;
        initFragment();
        initBottomNavigationView();
    }


    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new KnowledgeSystemFragment());
        mFragmentList.add(new NavigationV2Fragment());
        mFragmentList.add(new ProjectFragment());
       // mFragmentList.add(BaseHomeFragment.newInstance("我的"));
    }

    private void initBottomNavigationView() {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        // 预设定进来后,默认显示fragment
        addFragment(R.id.layout_pager, mFragmentList.get(0));
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

    /**
     * 设置侧滑item的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       if (item == mItemWelfare){
            L.d(TAG, "点击了福利");
           RouterCenter.toWelfareHome();
           closeDrawer();
        }else if (item == mItemVideo){
            L.d(TAG, "点击了视频");
           closeDrawer();
        }else if (item == mItemAboutUs){
            L.d(TAG, "点击了关于我们");
           closeDrawer();
        }else if (item == mItemLogout){
            L.d(TAG, "点击了退出");
           closeDrawer();
        }
        return true;
    }

    /**
     * 关闭侧滑
     */
    private void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onEvent(BaseEventbusBean event) {
        super.onEvent(event);
        int type = event.getType();

        if (EventBusBean.SHOP_MALL_HOME == type){
            //检查侧滑菜单的状态
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nav_header_login_tv){
            L.d(TAG, "点击了登录");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDrawer();
    }
}

