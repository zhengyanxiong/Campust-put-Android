package com.example.tome.module_shop_mall.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.example.tome.core.util.ImageUpload;

/**
 * WebView初始化
 */
public class WebViewInitUtils {

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    public static void init(Activity content,WebView webview,ImageUpload imageUpload) {
        // 默认缓存模式
        WebSettings settings = webview.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setMediaPlaybackRequiresUserGesture(true);
        // 设置支持javaScript
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSaveFormData(true);
        // 不保存密码
        settings.setSavePassword(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportMultipleWindows(false);
        settings.setLightTouchEnabled(true);
        // 不支持页面放大
        settings.setBuiltInZoomControls(false);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);
        // 滚动条
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 非常关键，否则设置了WebChromeClient后会出现乱码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        //自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBlockNetworkImage(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);


        //开启Android条用JavaScript
        webview.getSettings().setJavaScriptEnabled(true);


        //注册JavaScript条用手机上传文件
        if(null != imageUpload){
           webview.addJavascriptInterface(new JavaScriptUtils(content,imageUpload), "appImageObj");
        }

        //注册JavaScript跳转Android页面
        webview.addJavascriptInterface(new JavaScriptUtils(content),"toHomeActivity");
        webview.addJavascriptInterface(new JavaScriptUtils(content),"toMyCenterActivity");
        webview.addJavascriptInterface(new JavaScriptUtils(content),"toMySettingActivity");
        webview.addJavascriptInterface(new JavaScriptUtils(content),"toLoginActivity");

        //注册JavaScript调用Android发送消息通知
        webview.addJavascriptInterface(new JavaScriptUtils(content),"sendNotification1Obj");

    }

    public static String getHtmlData(String bodyHTML) {
        String head = "<head>" + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
            "<style>img{max-width: 100%; width:auto; height:auto;}</style>" + "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static String getVideoHtmlData(String videoUrl) {
        String video =
            "<p style=\"white-space:normal;\">" + "    <video  id=\"player\" name=\"player\" allowscriptaccess=\"always\" allowfullscreen=\"true\" src=" + "        \"" + videoUrl +
                "\" type=\"application/x-shockwave-flash\" controls=\"controls\"  width=\"100%\" autostart=\"false\" loop=\"true\" /></p>";
        return getHtmlData(video);
    }
}
