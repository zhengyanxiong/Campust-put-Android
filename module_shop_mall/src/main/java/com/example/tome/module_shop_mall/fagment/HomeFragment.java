package com.example.tome.module_shop_mall.fagment;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;

import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.widgetUtils.WebViewInitUtils;
import com.example.tome.module_shop_mall.activity.MainActivity;
import com.example.tome.projectCore.base.mvp.BaseVpListFragment;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.EventBusBean;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.greenrobot.eventbus.EventBus;

/**
 * @Created by TOME .
 * @时间 2018/5/30 16:51
 * @描述 ${首页}
 */

public class HomeFragment extends BaseVpFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View{
    @BindView(R2.id.home_web_view)
    WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.mall_activity_home_web;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        StatuBarCompat.setImmersiveStatusBarWithView(true,getActivity());
        mImmersionBar.transparentBar().init();
        WebViewInitUtils.init(getActivity(),webView);
        webView.loadUrl(Constants.HOME_PAGE_LINK);

        //点击拦截 true表示拦截, false表示不拦截
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(articleLink);
                return false;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

}
