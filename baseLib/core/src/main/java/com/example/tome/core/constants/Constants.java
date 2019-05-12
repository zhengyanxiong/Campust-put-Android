package com.example.tome.core.constants;

import com.example.tome.core.base.BaseHost;

/**
 * Author: created by Bernie on 2019/3/25
 **/
public interface Constants {
    String APP_MEMBER_TOCKEN = "app_member_token";

    String MEMBER_TOCKEN = "member";

    //h5工程地址
    String HOME_PAGE_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000";

    //h5发布商品地址
    String PUBLISH_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/good/publishGoodsToAdr";

    //h5我发布的商品详情
    String MY_PUBLISH_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/good/myPublishGoods";
    //h5我卖出的商品详情
    String MY_SELL_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/user/sellManage";
    //h5我买到的商品详情
    String MY_BUY_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/user/buyManage";

    //上传图片到服务器地址
    String NGINGX_UPLOAD_LINK = BaseHost.CAMPUS_SERVER_HOST+":8788/file/fileUpload";

    //用户首页
    String USER_HOME_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/user/userHome";

    //用户信息资料
    String USER_INFORMATION_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/information";

    //用户注册
    String USER_REGISTER_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/register";

    //商品分类
    String CLASSIFY_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/getGoodsClass";

    //学生认证
    String STUDENT_AUTHENTICATION = BaseHost.CAMPUS_SERVER_HOST+":8000/studentId";
    //收货地址
    String USER_MYADDRESS = BaseHost.CAMPUS_SERVER_HOST+":8000/user/myAddress";

    //意见反馈地址
    String OPTION_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/opinion";
    //用户行为规范地址
    String USER_RULERS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/rulesUser";
    //关于校园拍拍地址
    String ABOUT_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/about";
}
