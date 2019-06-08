package com.example.tome.module_shop_mall.fagment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.ValueCallback;
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
import com.example.tome.module_shop_mall.widget.WebViewInitUtils;
import com.example.tome.module_shop_mall.base.Cookies;
import com.example.tome.module_shop_mall.util.BasicTool;
import com.example.tome.module_shop_mall.widget.ImageAlertDialogs;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.fec.core.router.arouter.RouterURLS;

import java.util.HashMap;
import java.util.Map;


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

        WebViewInitUtils.init(getActivity(),webView,null);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getActivity().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

        String token = BasicTool.getMemberToken(getActivity(),Constants.MEMBER_TOCKEN,"");
        if(null == token || "".equals(token)){
            L.d("未登录，开始设置token为空.................");
            Cookies.synCookies(getActivity(),Constants.HOME_PAGE_LINK,Constants.APP_MEMBER_TOCKEN,"");
        } else {
            L.d("已登录，开始设置token{"+token+"}..................");
            Cookies.synCookies(getActivity(),Constants.HOME_PAGE_LINK,Constants.APP_MEMBER_TOCKEN,token);
        }

        webView.loadUrl(Constants.HOME_PAGE_LINK);

        String isPaySuccess = getActivity().getIntent().getStringExtra("isPaySuccess");
        if ("1".equals(isPaySuccess)){
            String outTradeNo = getActivity().getIntent().getStringExtra("outTradeNo");
            String totalAmount = getActivity().getIntent().getStringExtra("totalAmount");
            String tradeNo = getActivity().getIntent().getStringExtra("tradeNo");
            webView.loadUrl(Constants.PAY_SECCESS_LINK+"?outTradeNo="+outTradeNo+"&totalAmount="+totalAmount+"&tradeNo="+tradeNo+"&state="+isPaySuccess);
        }

        //点击拦截 true表示拦截, false表示不拦截
        webView.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(articleLink);
                String url = request.getUrl().toString();
                L.d("接收到的URL是："+url);
                if (url.contains("aliPay"))
                    return openApp(url);
                return false;

            }

            //判断app是否安装
            /*private boolean isInstall(Intent intent) {
                return Application.getInstance().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
            }
*/
            //打开app
            private boolean openApp(String url) {
                if (TextUtils.isEmpty(url)) return false;
                try {
                    if (!url.startsWith("http") || !url.startsWith("https") || !url.startsWith("ftp")) {
                        Uri uri = Uri.parse(url);
                        String host = uri.getHost();
                        String scheme = uri.getScheme();
                        //host 和 scheme 都不能为null
                        if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(scheme)) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            //if (isInstall(intent)) {
                                startActivity(intent);
                                return false;
                           // }
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


            String referer = "商户申请H5时提交的授权域名";

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 获取上下文, H5PayDemoActivity为当前页面
                final Activity context = getActivity();

                // ------  对alipays:相关的scheme处理 -------
                if(url.startsWith("alipays:") || url.startsWith("alipay")) {
                    try {
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    } catch (Exception e) {
                        new AlertDialog.Builder(context)
                                .setMessage("未检测到支付宝客户端，请安装后重试。")
                                .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri alipayUrl = Uri.parse(url);
                                        context.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                                    }
                                }).setNegativeButton("取消", null).show();
                    }
                    return true;
                }
                // ------- 处理结束 -------

                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }

                view.loadUrl(url);
                return true;
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
