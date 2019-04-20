package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.widget.ActionSheetDialog;
import com.example.tome.core.util.ImageUpload;

import butterknife.BindView;

public class StudentAuthenticationActivity extends BaseVcActivity{
    @BindView(R2.id.my_student_authentication_web_view)
    WebView webView;
    private ImageUpload imageUpload;

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_student_authentication;
    }

    @Override
    public void initTitle() {

    }

    Js js=null;
    @Override
    public void initView() {

        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.bar_grey).init();
        StatuBarCompat.setImmersiveStatusBar(true,R.color.comment_text,this);

        imageUpload = new ImageUpload(this);
        imageUpload.setOnImageUploadResult(this);

        webView.getSettings().setJavaScriptEnabled(true);
        js = new Js();
        webView.addJavascriptInterface(js, "appImageObj");

        WebViewInitUtils.init(this, webView,null);

        webView.loadUrl(Constants.STUDENT_AUTHENTICATION);
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
        setPlatformType(url);
    }

    class Js {
        String flag;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        @JavascriptInterface
        public void showImageDialog(String flag) {
            this.flag = flag;
            shows();
        }

    }

    public void shows() {
        new ActionSheetDialog(getContext())
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        imageUpload.openCamera(getContext());
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
                        webView.loadUrl("javascript: uploadAvatarIsCompleted('"+js.getFlag()+"','" + url + "')");
                    }
                });
            }
        }).start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (imageUpload != null && resultCode == RESULT_OK) {
            imageUpload.onActivityResult(requestCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
