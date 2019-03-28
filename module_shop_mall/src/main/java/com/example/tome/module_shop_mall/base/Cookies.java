package com.example.tome.module_shop_mall.base;

import android.app.Application;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.util.HashMap;

/**
 * Author: created by Bernie on 2019/3/28
 **/
public class Cookies {


    /**
     * 同步设置cookie
     * @param context
     * @param url
     * @param key
     * @param value
     */
    public static void synCookies(Context context, String url,String key,String value) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        cookieManager.setCookie(url, String.format("%s=%s", key, value));
        CookieSyncManager.getInstance().sync();
    }

    private HashMap<String,String> cookieMap;

    /**
     * 清楚所有的cookie
     */
    public void clearAll(){
        cookieMap.clear();
    }

    /**
     * 更据key获得cookie
     * @param key
     * @return
     */
    public String getCookie(String key){
        return cookieMap.get(key);
    }

    /**
     * 设置cookie
     * @param key
     * @param value
     */
    public void setCookie(String key,String value){
        cookieMap.put(key,value);
    }

}
