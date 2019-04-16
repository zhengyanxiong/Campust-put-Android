package com.example.tome.module_shop_mall.widget;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.activity.MainActivity;
import com.example.tome.module_shop_mall.activity.PublishiGoodsActivity;
import com.example.tome.module_shop_mall.arouter.RouterCenter;

public class JavaScriptUtils {
    private Activity activity;

    public JavaScriptUtils(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //跳转到首页
    @JavascriptInterface
    public void startHomeIntent() {
        L.d("调用了跳转首页");
        Intent intent = new Intent(activity,MainActivity.class);
        intent.putExtra("homeWeb",1);
        activity.startActivity(intent);
    }

    //跳转到个人中心页面
    @JavascriptInterface
    public void startMycenterIntent() {
        L.d("跳转到个人中心页面");
        Intent intent = new Intent(activity,MainActivity.class);
        intent.putExtra("toMyCenter",4);
        activity.startActivity(intent);
    }

    //跳转到个人中心设置
    @JavascriptInterface
    public void startMySettingIntent() {
        L.d("跳转到个人中心设置");
        RouterCenter.toMyCenterSetting();
    }
}
