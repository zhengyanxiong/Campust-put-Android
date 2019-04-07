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
    String PUBLISH_GOODS_LINK = BaseHost.CAMPUS_SERVER_HOST+":8000/good/publishGoods";

    //上传图片到服务器地址
    String NGINGX_UPLOAD_LINK = BaseHost.CAMPUS_SERVER_HOST+":8788/file/fileUpload";
}
