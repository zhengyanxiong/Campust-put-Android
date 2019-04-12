package com.example.tome.module_shop_mall.fagment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.widgetUtils.WebViewInitUtils;
import com.example.tome.module_shop_mall.activity.MainActivity;
import com.example.tome.module_shop_mall.base.Cookies;
import com.example.tome.module_shop_mall.util.BasicTool;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.fec.core.router.arouter.RouterURLS;

import java.io.File;

import static android.app.Activity.RESULT_OK;


/**
 * @Created by TOME .
 * @时间 2018/5/30 16:51
 * @描述 ${首页}
 */
@Route(path = RouterURLS.MALL_HOME_WEB)
@SuppressLint("ValidFragment")
public class HomeFragment extends BaseVpFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {
    @BindView(R2.id.home_web_view)
    WebView webView;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private ImageAlertDialogs selectImageDialog;
    private AlertView alertView;
    OnItemClickListener onItemClickListener;

    public HomeFragment(){}

    public HomeFragment(ImageAlertDialogs selectImageDialog){
        this.selectImageDialog = selectImageDialog;
    }

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
        String token = BasicTool.getMemberToken(getActivity(),Constants.MEMBER_TOCKEN,"");
        if(null == token || "".equals(token)){
            L.d("未登录，开始设置token为空.................");
            Cookies.synCookies(getActivity(),Constants.HOME_PAGE_LINK,Constants.APP_MEMBER_TOCKEN,"");
        } else {
            L.d("已登录，开始设置token{"+token+"}..................");
            Cookies.synCookies(getActivity(),Constants.HOME_PAGE_LINK,Constants.APP_MEMBER_TOCKEN,token);
        }
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
