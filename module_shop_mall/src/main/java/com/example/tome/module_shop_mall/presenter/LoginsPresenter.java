package com.example.tome.module_shop_mall.presenter;

import android.app.Activity;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.ActivityUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.SPUtil;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.LoginBean;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.ILoginsContract;
import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.model.LoginsModel;
import com.example.tome.projectCore.bean.BaseObj;

import java.util.Map;
import java.util.Observer;

import io.reactivex.Observable;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/11  11:30
 * - generate by MvpAutoCodePlus plugin.
 */

public class LoginsPresenter extends BasePresenter<ILoginsContract.View,ILoginsContract.Model> implements ILoginsContract.Presenter {

    public Activity lCurrentActivity;

    public LoginsPresenter(Activity activity){
        lCurrentActivity = activity;
    }

    @Override
    public void attachView(ILoginsContract.View view) {
        super.attachView(view);
        //lCurrentActivity = ActivityUtils.getInstance().currentActivity();
    }

    @Override
    protected ILoginsContract.Model createModel() {
        return new LoginsModel();
    }

    @Override
    public void login(LoginBean loginBean) {
        addDisposable(ModelVcService.getRemoteData(true, mView, new ModelVcService.MethodSelect<Map<String,String>>() {
            @Override
            public Observable<BaseObj<Map<String,String>>> selectM(ApiService service) {
                return service.login(loginBean);
            }
        }, new INetCallback<Map<String,String>>() {
            @Override
            public void onSuccess(Map<String,String> result) {
                L.d("登录-------", result);
                SPUtil.put(lCurrentActivity,Constants.MEMBER_TOCKEN,result.get("token"));
                mView.loginSuccess();
            }

        }));

        /*addDisposable(mModel.login(loginBean)
            .subscribeWith(new BaseObserver<BaseObj<Map<String,String>>>(mView){
                @Override
                public void onNext(BaseObj<Map<String,String>> loginBeanBaseObj) {
                    L.d("登录返回值："+loginBeanBaseObj.toString());
                    if("200".equals(loginBeanBaseObj.getCode()) && "success".equals(loginBeanBaseObj.getMessage())){
                        L.d("登录成功返回的token："+loginBeanBaseObj.getData().get("token"));
                        SPUtil.put(lCurrentActivity,Constants.MEMBER_TOCKEN,loginBeanBaseObj.getData().get("token"));
                        mView.loginSuccess();
                    } else {
                        mView.loginFailure();
                    }
                }
            }));*/
    }

}

