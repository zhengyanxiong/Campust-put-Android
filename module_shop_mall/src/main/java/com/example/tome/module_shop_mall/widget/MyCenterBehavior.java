package com.example.tome.module_shop_mall.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * Author: created by Bernie on 2019/3/17
 **/
public class MyCenterBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "kkk";

    private TextView textView;
    private float imgHeight;
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public MyCenterBehavior() {
    }

    public MyCenterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //根据target和nestedScrollAxes决定此view是否要与target配合进行嵌套滚动，
//并返回true(要与target配合进行嵌套滚动)或false(不与target配合进行嵌套滚动)。
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull View child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                  @NonNull View target, int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        float translationY = target.getTranslationY();
        float offsetY ;

        //还未平移到顶的情况下
        if (translationY > 0) {
            offsetY = translationY - dy;
            //手指上滑, dy>0, offset减小, target向上平移,
            //如果手指上滑过快, dy数值会过大, 造成offset<0,需要控制其平移到顶即可
            if(offsetY < 0){
                offsetY = 0;
            }
            //手指下滑, dy<0, offset加大, target向下平移,
            //如果手指下滑过快, dy数值会过大, 造成offset>imgHeight, 需要控制其平移到底即可
            if (offsetY > imgHeight) {
                offsetY = imgHeight;
            }
            //target平移
            target.setTranslationY(offsetY);

            //child透明度变化
            float alpha = (float) (0.5 + 0 * offsetY / imgHeight);
            child.setAlpha(alpha);

        }

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }
}
