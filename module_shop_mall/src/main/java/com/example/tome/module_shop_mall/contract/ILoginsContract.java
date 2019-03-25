package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.module_shop_mall.bean.LoginBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.projectCore.bean.BaseObj;

import java.util.Map;

import io.reactivex.Observable;


public interface ILoginsContract {
    interface View extends IView {
        //登录成功
        void loginSuccess();
        //登录失败
        void loginFailure();

    }

    interface Presenter extends IPresenter<View> {
        void login(LoginBean loginBean);
    }

    interface Model extends IModel {
        Observable<BaseObj<Map<String,String>>> login(LoginBean loginBean);
    }
}
