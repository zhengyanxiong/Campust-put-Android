package com.example.tome.module_shop_mall.fagment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.constants.Constants;
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class PublishGoods extends BaseVpFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {
    @BindView(R2.id.publish_goods_web_view)
    WebView webView;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private ImageAlertDialogs selectImageDialog;
    private AlertView alertView;
    OnItemClickListener onItemClickListener;

    public PublishGoods(){}
    public PublishGoods(ImageAlertDialogs selectImageDialog){
        this.selectImageDialog = selectImageDialog;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_publishi_goods;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        WebViewInitUtils.init(getActivity(),webView,null);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.addJavascriptInterface(new Js(),"appImageObj");
        webView.loadUrl(Constants.PUBLISH_GOODS_LINK);
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

       /* webView.setWebChromeClient(new WebChromeClient(){
            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                selectImageDialog.show();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                selectImageDialog.show();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                selectImageDialog.show();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                selectImageDialog.show();
                return true;
            }
        });*/


    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    class Js{
        @JavascriptInterface
        public void showImageDialog(String flag){
            selectImageDialog.show();
        }
    }

}
