package com.example.tome.module_shop_mall.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.tome.module_shop_mall.R;

/**
 * Author: created by Bernie on 2019/3/28
 * 个人中心用户信息数据管理显示
 **/
public class MyCenterNumInfor extends LinearLayout {
    public MyCenterNumInfor(Context context) {
        super(context);
    }

    public MyCenterNumInfor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mall_user_infor_msg,this);
    }

    public MyCenterNumInfor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
