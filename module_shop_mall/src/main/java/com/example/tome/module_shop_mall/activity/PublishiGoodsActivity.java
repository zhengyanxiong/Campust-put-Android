package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
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
import com.example.tome.module_shop_mall.base.BaseActivity;
import com.example.tome.module_shop_mall.widget.ActionSheetDialog;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;
import com.example.tome.module_shop_mall.widget.ImageUpload;

import butterknife.BindView;

public class PublishiGoodsActivity extends BaseVcActivity implements ImageUpload.OnImageUploadResult {
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

        imageUpload = new ImageUpload(this);
        imageUpload.setOnImageUploadResult(this);

        WebViewInitUtils.init(this, webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Js(), "appImageObj");
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

    public void shows() {
        new ActionSheetDialog(getContext())
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                })
                .addSheetItem("从相册中选择", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        imageUpload.selectImage();
                    }
                }).show();
    }

    public void setPlatformType(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript: uploadAvatarIsCompleted('" + url + "')");
                    }
                });
            }
        }).start();

    }


    @Override
    public void getImageUrl(String url) {
        L.d("getImageUrl" + url);
        setPlatformType(url);
    }

    class Js {
        @JavascriptInterface
        public void showImageDialog() {
            shows();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (imageUpload != null && resultCode == RESULT_OK) {
            imageUpload.onActivityResult(requestCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
