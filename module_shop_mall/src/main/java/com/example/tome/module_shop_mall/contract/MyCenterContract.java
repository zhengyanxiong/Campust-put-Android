package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.bean.UserAllInfor;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.projectCore.bean.BaseObj;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public interface MyCenterContract {
    interface View extends IView{
        void getUserInfor(UserInfor userInfor);

        void getUserGoodsInfor(UserAllInfor userAllInfor);
    }

    interface Presenter extends IPresenter<View>{
        /**
         * 获得个人中心中的数据
         */
        void getUserInfor(String token);
        /**
         * 获得用户商品信息
         */
        void getUserGoodsInfor(Integer userId);
    }

    interface Model extends IModel {

        /**
         * 获得个人中心中的数据
         */
        Observable<BaseObj<UserInfor>> getUserInfor(String token);

        /**
         * 获得用户商品信息
         */
        Observable<BaseObj<UserAllInfor>> getUserGoodsInfor(Integer userId);


    }
}
