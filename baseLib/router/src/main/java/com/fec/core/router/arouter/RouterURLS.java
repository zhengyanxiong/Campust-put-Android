package com.fec.core.router.arouter;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:20
 * @描述 ${路由地址}
 */
// 注意事项！！！  /模块/页面名称   或  /模块/子模块../页面名称   至少两级  例子 /商城/商品详情 /shop/shopDetails
    //url 第一次相同会报错??
public interface RouterURLS {

    String MEMBER_INFORMATION = "/member/information";
    String BASE_MAIN = "/base/main";
    String SHOP_CART_MAIN = "/shoping/cart/main";
    String SHOP_MALL_HOME = "/mall/home";
    String SHOP_GOODS = "/shop1/goods";
    String MVC_TEST = "/mvc/test";
    String MVP_TEST = "/mvp/test";
    String MVP_TEST2 = "/mvp/test2";
    String WELFARE_HOME = "/welfare/home";
    String WELFARE_PREVIEW = "/welfare/preview";


    String PRODUCT_GOODS = "/product1/goods";
    String PRODUCT_CART = "/product/cart";

    String CUSTOM_CONTROL = "/custom/control";

    String MALL_HOME_WEB = "/mall/homeweb";

    String MALL_MYCENTER = "/mall/mycenter";
    String MYCENTER_SETTING = "/mycenter/setting";
    String GOODS_CLASSIFY = "/mall/goodsclassify";
    String USER_LGINT = "/mall/login";

}
