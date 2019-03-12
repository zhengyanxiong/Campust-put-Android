package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.model.MyCenterModel;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public class MyCenterPresenter extends BasePresenter<MyCenterContract.View,MyCenterContract.Model> implements MyCenterContract.Presenter {
    @Override
    protected MyCenterContract.Model createModel() {
        return new MyCenterModel();
    }

    @Override
    public void getMyCenterClassifyData() {
        L.d("this is getMyCenterClassifyData........");
    }
}
