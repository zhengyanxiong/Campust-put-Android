package com.example.tome.module_shop_mall.model;

import com.example.tome.core.base.mvp.DisposablePool;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVpService;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.projectCore.bean.BaseObj;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: created by Bernie on 2019/3/12
 **/
public class MyCenterModel extends DisposablePool implements MyCenterContract.Model {

    @Override
    public Observable<BaseObj<UserInfor>> getUserInfor(String token) {
        return  ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<UserInfor>() {
            @Override
            public Observable<BaseObj<UserInfor>> selectM(ApiService service) {
                return service.getUserInfor(token);
            }
        });
    }
}
