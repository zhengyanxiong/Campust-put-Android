package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import butterknife.BindView;

import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.base.mvc.BaseVcFragment;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.constants.IntentKVCore;
import com.example.tome.core.util.ImageUpload;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;

import static android.app.Activity.RESULT_OK;

public class IMFragment extends BaseVcFragment{
    @BindView(R2.id.im_web_view)
    WebView webView;
    private ImageUpload imageUpload;

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_knowledge_system;
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initView() {
        //注册EventBus
        super.regEvent = true;

        imageUpload = new ImageUpload(getActivity());

        WebViewInitUtils.init(getActivity(),webView,imageUpload);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getActivity().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.loadUrl(Constants.IM_PAGE_LINK);


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

    public void sendImageUrl(String url){
        setPlatformType(url);
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

}
