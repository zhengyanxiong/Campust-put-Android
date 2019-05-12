package com.example.tome.module_shop_mall.widget;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.tome.core.util.ImageUpload;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StringUtils;
import com.example.tome.module_shop_mall.activity.MainActivity;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.util.BasicTool;
import com.facebook.stetho.common.StringUtil;

public class JavaScriptUtils{
    public static String flag = "";
    private Activity activity;
    private ImageUpload imageUpload;

    public JavaScriptUtils(Activity activity){
        this.activity = activity;
    }
    public JavaScriptUtils(Activity activity,ImageUpload imageUpload){
        this.activity = activity;
        this.imageUpload = imageUpload;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ImageUpload getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(ImageUpload imageUpload) {
        this.imageUpload = imageUpload;
    }

    /**
     * 调用手机上传文件
     * @param flag
     */
    @JavascriptInterface
    public void showImageDialog(String flag) {
        if(BasicTool.isNotEmpty(flag)){
            JavaScriptUtils.flag = flag;
        }
        new ActionSheetDialog(activity)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        imageUpload.openCamera(activity);
                    }
                })
                .addSheetItem("从相册中选择", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        imageUpload.selectImage();
                    }
                }).show();
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

    //跳转到登陆页面
    @JavascriptInterface
    public void startLoginIntent() {
        L.d("跳转到登陆页面");
        RouterCenter.toLogin();
    }


}
