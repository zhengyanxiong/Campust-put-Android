package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.widgetUtils.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.widget.ActionSheetDialog;
import com.example.tome.module_shop_mall.widget.ImageUpload;

import butterknife.BindView;

public class UserHomeActivity extends BaseVcActivity {
    @BindView(R2.id.user_home_web_view)
    WebView webView;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_user_home_web;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        WebViewInitUtils.init(this, webView);

        webView.loadUrl(Constants.USER_HOME_LINK);
        //点击拦截 true表示拦截, false表示不拦截
        webView.setWebViewClient(new WebViewClient() {
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

}
