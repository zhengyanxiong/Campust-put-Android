package com.example.tome.module_shop_mall.model;

import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.api.ModelVpService;
import com.example.tome.module_shop_mall.bean.LoginBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.ILoginsContract;
import com.example.tome.core.base.mvp.DisposablePool;
import com.example.tome.projectCore.bean.BaseObj;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/11  11:30
 * - generate by MvpAutoCodePlus plugin.
 */

public class LoginsModel extends DisposablePool implements ILoginsContract.Model {

    @Override
    public Observable<BaseObj<Map<String,String>>> login(LoginBean loginBean) {
        return ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<Map<String,String>>() {
            @Override
            public Observable<BaseObj<Map<String,String>>> selectM(ApiService service) {
                return service.login(loginBean);
            }
        });
    }

    /*@Override
    public Observable<BaseObj<UserInfor>> getUserInfor(String token) {
        return ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<UserInfor>() {
            @Override
            public Observable<BaseObj<UserInfor>> selectM(ApiService service) {
                return service.getUserInfor(token);
            }
        });
    }*/

}

