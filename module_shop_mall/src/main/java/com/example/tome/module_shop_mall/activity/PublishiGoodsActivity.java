package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.core.util.ImageUpload;

import butterknife.BindView;

public class PublishiGoodsActivity extends BaseVcActivity {
    @BindView(R2.id.publish_goods_web_view)
    WebView webView;
    private ImageUpload imageUpload;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;


    @Override
    protected int getLayoutId() {
        return R.layout.mall_fragment_publishi_goods;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.bar_grey).init();
        StatuBarCompat.setImmersiveStatusBar(true,R.color.comment_text,this);

        imageUpload = super.getImageUpload();
        //imageUpload.setOnImageUploadResult(this);

        WebViewInitUtils.init(this, webView,imageUpload);
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.addJavascriptInterface(new JavaScriptUtils(this,imageUpload), "appImageObj");
        //webView.addJavascriptInterface(new Js(),"toHomeActivity");
        webView.loadUrl(Constants.PUBLISH_GOODS_LINK);
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


    @Override
    public void getImageUrl(String url) {
        L.d("getImageUrl" + url);
        setPlatformType(url,webView);
    }

}
