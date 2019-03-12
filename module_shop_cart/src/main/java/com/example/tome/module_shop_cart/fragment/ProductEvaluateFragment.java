package com.example.tome.module_shop_cart.fragment;

import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.listener.OnDimensionSelectListener;

/**
 * @author tome
 * @date 2018/8/10  15:44
 * @describe ${商品详情-评论}
 */
public class ProductEvaluateFragment extends CommonFragment implements OnDimensionSelectListener{
    @Override
    public int setContentLayout() {
        return R.layout.cart_fragment_evaluate;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onSelectDemension(String dimensions,String selectCout) {

    }

    @Override
    public void onProductIntroShow(GetProductDetailsBean.DataBean bean) {

    }

    @Override
    public void onPriceChange(String price) {

    }
}
