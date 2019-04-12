package com.example.tome.module_shop_mall.widget;


import android.webkit.JavascriptInterface;

import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.arouter.RouterCenter;

public class ToActivity {

    //跳转到首页
    @JavascriptInterface
    public void startHomeIntent() {
        L.d("调用了跳转首页");
        RouterCenter.toHomeWeb();
    }

    //跳转到个人中心
    @JavascriptInterface
    public void startMyCenterIntent() {
        RouterCenter.toMyCenter();
    }

}
