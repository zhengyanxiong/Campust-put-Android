package com.example.tome.module_shop_mall.activity;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.ImageUpload;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;

import butterknife.BindView;

public class OpinionActivity extends BaseVcActivity {
    @BindView(R2.id.opinion_web_view)
    WebView webView;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_fragment_opinion;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        WebViewInitUtils.init(this, webView,null);

        webView.loadUrl(Constants.OPTION_LINK);
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
