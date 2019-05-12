package com.example.tome.core.base.mvc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.R;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.constants.BaseApplication;
import com.example.tome.core.constants.IntentKVCore;
import com.example.tome.core.helper.HUDFactory;
import com.example.tome.core.util.ImageUpload;
import com.example.tome.core.util.L;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.logger.Logger;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Created by TOME .
 * @时间 2018/6/26 17:40
 * @描述 ${MVC模式的Base Activity}
 */

public abstract class BaseVcActivity extends AppCompatActivity implements IView , ImageUpload.OnImageUploadResult{

    private Unbinder unBinder;
    protected boolean regEvent;
    public BaseVcActivity mActivity ;
    public KProgressHUD kProgressHUD;
    protected boolean isDestory = false;
    //管理事件流订阅的生命周期CompositeDisposable
    private CompositeDisposable compositeDisposable;
    public ImmersionBar mImmersionBar;
    public IView mView = this;
    private ImageUpload imageUpload;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        //加入activity管理
        BaseApplication.getAppContext().getActivityControl().addActivity(this);
        //沉浸式状态栏
        initImmersionBar();
        //setImmeriveStatuBar();
        mActivity = this ;

        imageUpload = new ImageUpload(this);
        imageUpload.setOnImageUploadResult(this);

        initTitle();
        initView();
        if (regEvent){
            EventBus.getDefault().register(this);
        }
    }

    public ImageUpload getImageUpload(){
        return this.imageUpload;
    }

    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        // 所有子类都将继承这些相同的属性,暂时先不加,会导入全部状态栏都一致
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.mainColor).init();
        StatuBarCompat.setImmersiveStatusBar(true,R.color.comment_text,this);
    }

    /**
     * rxjava管理订阅者
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showHUD(String msg) {
        if (isDestory){
            return;
        }
        if (TextUtils.isEmpty(msg)){
            msg = getString(R.string.loading);
        }
        kProgressHUD = HUDFactory.getInstance().creatHUD(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(TextUtils.isEmpty(msg) ? getString(R.string.loading) : msg)
               // .setLabel(null)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f).show();
    }

    @Override
    public void dismissHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    /**
     * 提示网络请求错误信息
     * @param msg
     * @param code
     */
    @Override
    public void showError(String msg, String code) {
        String mCode ="-1";
        if (mCode.equals(code) || !"200".equals(code)){
            ToastUtils.showShort(mActivity, msg);
        }

    }

    /**
     * 空界面显示
     */
    @Override
    public void showNormal() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showError() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i("当前运行的activity:" + getClass().getName());
    }

    /**
     * 销毁
     * @param v
     */
    public void back(View v) {
        finish();
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        BaseApplication.getAppContext().getActivityControl().finishiAll();
        //用于杀掉当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //参数0和1代表退出的状态，0表示正常退出，1表示异常退出
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除订阅关系
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }

        isDestory = true;
        dismissHUD();
        //移除类
        BaseApplication.getAppContext().getActivityControl().removeActivity(this);

    }



    /**
     * 沉浸式状态栏
     */
    protected void setImmeriveStatuBar() {
        StatuBarCompat.setImmersiveStatusBar(true, Color.WHITE, this);

    }


    public void setPlatformType(final String url, WebView webView) {
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
    public void setPlatformTypeWithFlag(final String url,final String flag, WebView webView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript: uploadAvatarIsCompleted('"+flag+"','" + url + "')");
                    }
                });
            }
        }).start();
    }


    @Override
    public void getImageUrl(String url) {
        L.d("getImageUrl" + url);
        //setPlatformType(url);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (imageUpload != null && resultCode == RESULT_OK && requestCode == IntentKVCore.FLAG_IMAGE_FROM_ALBUM) {
            imageUpload.onActivityResult(requestCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView();



}
