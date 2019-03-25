package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.model.MyCenterModel;
import com.example.tome.projectCore.bean.BaseObj;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public class MyCenterPresenter extends BasePresenter<MyCenterContract.View,MyCenterContract.Model> implements MyCenterContract.Presenter {
    @Override
    protected MyCenterContract.Model createModel() {
        return new MyCenterModel();
    }


    @Override
    public void getUserInfor(String token) {
        addDisposable(mModel.getUserInfor(token)
                .subscribeWith(new BaseObserver<BaseObj<UserInfor>>() {
                    @Override
                    public void onNext(BaseObj<UserInfor> userInforBaseObj) {
                        L.d("获得用户信息："+userInforBaseObj.toString());
                        mView.getUserInfor(userInforBaseObj.getData());
                    }
                }));
    }
}
