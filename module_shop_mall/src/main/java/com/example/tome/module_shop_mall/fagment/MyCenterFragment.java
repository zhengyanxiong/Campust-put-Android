package com.example.tome.module_shop_mall.fagment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.constants.Constants;
import com.example.tome.core.util.JsonUtil;
import com.example.tome.core.util.L;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.core.util.UltimateBar;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.LoginActivity;
import com.example.tome.module_shop_mall.activity.MainActivity;
import com.example.tome.module_shop_mall.activity.MyGoodShopCarlActivity;
import com.example.tome.module_shop_mall.activity.MyGoodsBuyActivity;
import com.example.tome.module_shop_mall.activity.MyGoodsPublshActivity;
import com.example.tome.module_shop_mall.activity.MyGoodsSellActivity;
import com.example.tome.module_shop_mall.activity.OpinionActivity;
import com.example.tome.module_shop_mall.activity.RulesUserActivity;
import com.example.tome.module_shop_mall.activity.StudentAuthenticationActivity;
import com.example.tome.module_shop_mall.activity.UserHomeActivity;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.NoticeMessage;
import com.example.tome.module_shop_mall.bean.PayNoticeMessage;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.bean.UserAllInfor;
import com.example.tome.module_shop_mall.bean.UserInfor;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.contract.MyCenterContract;
import com.example.tome.module_shop_mall.mqtt.IGetMessageCallBack;
import com.example.tome.module_shop_mall.mqtt.MQTTService;
import com.example.tome.module_shop_mall.mqtt.MqttPushClient;
import com.example.tome.module_shop_mall.mqtt.MyServiceConnection;
import com.example.tome.module_shop_mall.mqtt.PushCallback;
import com.example.tome.module_shop_mall.presenter.MyCenterPresenter;
import com.example.tome.module_shop_mall.util.DateUtiles;
import com.example.tome.module_shop_mall.widget.JavaScriptUtils;
import com.fec.core.router.arouter.RouterURLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.luck.picture.lib.tools.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import retrofit2.http.Path;

/**
 * Author: created by Bernie on 2019/3/12
 **/
@Route(path = RouterURLS.MALL_MYCENTER)
public class MyCenterFragment extends BaseVpFragment<MyCenterContract.View, MyCenterContract.Presenter> implements MyCenterContract.View,IGetMessageCallBack {

    /*@BindView(R2.id.title_my_center_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_my_center_right_icon)
    ImageView mTitleRightIcon;*/
    @BindView(R2.id.mycenter_toolbar)
    Toolbar toolbar;
    @BindView(R2.id.btn_start_login)
    Button startButton;
    @BindView(R2.id.title_user_img)
    RelativeLayout userImg;
    @BindView(R2.id.showUserInfor)
    RelativeLayout showUserInfor;
    @BindView(R2.id.tv_name)
    TextView username;
    @BindView(R2.id.tv_email)
    TextView userEmail;
    @BindView(R2.id.img_avatar)
    ImageView userHead;
    @BindView(R2.id.mycenter_num_publish)
    TextView myPublish;
    @BindView(R2.id.mycenter_num_sell)
    TextView mySellNum;
    @BindView(R2.id.mycenter_num_buy)
    TextView myBuyNum;
    @BindView(R2.id.mycenter_num_collection)
    TextView myBuyCar;
    @BindView(R2.id.tv_has_authentication)
    TextView authenticationText;
    @BindView(R2.id.tv_signature)
    TextView userSignature;
    @BindView(R2.id.lay_sign)
    LinearLayout authenticationBtn;
    @BindView(R2.id.my_publish_goods_btn)
    LinearLayout myPublishBtn;
    @BindView(R2.id.my_sell_goods_btn)
    LinearLayout mySellGoodsBtn;
    @BindView(R2.id.my_buy_goods_btn)
    LinearLayout myBuyGoodsBtn;
    @BindView(R2.id.user_feedback)
    LinearLayout user_feedback;
    @BindView(R2.id.campus_ruler)
    LinearLayout campus_ruler;
    @BindView(R2.id.my_shop_car_btn)
    LinearLayout myShopCarBtn;
    private static String userToken = "";

    private PushCallback pushCallback;

    private MyServiceConnection serviceConnection;
    private MQTTService mqttService;


    @Override
    public MyCenterContract.Presenter createPresenter() {
        return new MyCenterPresenter();
    }

    @Override
    public MyCenterContract.View createView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        setHasOptionsMenu(true);

        pushCallback = new PushCallback();
        /*pushCallback.setGetMqttMessage(this);*/

        mImmersionBar.fitsSystemWindows(true).statusBarColor("#F4942300").init();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((AppCompatActivity) getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        myPublishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), MyGoodsPublshActivity.class));
                }
            }
        });
        mySellGoodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), MyGoodsSellActivity.class));
                }
            }
        });
        myBuyGoodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), MyGoodsBuyActivity.class));
                }
            }
        });
        myShopCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), MyGoodShopCarlActivity.class));
                }
            }
        });
        user_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userToken.isEmpty()){
                    startActivity(new Intent((AppCompatActivity) getActivity(), OpinionActivity.class));
                }
            }
        });
        campus_ruler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent((AppCompatActivity) getActivity(), RulesUserActivity.class));
            }
        });
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_mycenter;
    }

    @Override
    protected void initTitle() {
        /*mTitleContentText.setText("个人中心");
        mTitleRightIcon.setImageResource(R.drawable.icon_settings);
        mTitleRightIcon.setVisibility(View.VISIBLE);*/
    }

    @Override
    protected void initView() {
        StatuBarCompat.setTranslucentStatus(getActivity());
        //判断用户是否登录
        String memberToken = (String) SPUtil.get((AppCompatActivity) getActivity(), Constants.MEMBER_TOCKEN, "");
        userToken = memberToken;
        RelativeLayout.LayoutParams rl_user = (RelativeLayout.LayoutParams) userImg.getLayoutParams();
        if (null == memberToken || "".equals(memberToken)) {
            ToastUtils.showCenter("用户未登录");
            rl_user.setMargins(0, 26, 0, 0);
            rl_user.addRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            showUserInfor.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
            myPublish.setText("-");
            mySellNum.setText("-");
            myBuyNum.setText("-");
            myBuyCar.setText("-");
        } else {
            mPresenter.getUserInfor(memberToken);

            ToastUtils.showCenter(memberToken);
            rl_user.setMargins(25, 56, 0, 0);
            rl_user.removeRule(RelativeLayout.CENTER_HORIZONTAL);
            userImg.setLayoutParams(rl_user);
            startButton.setVisibility(View.GONE);
            showUserInfor.setVisibility(View.VISIBLE);

            userHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), UserHomeActivity.class));
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        L.d("onCreateOptionsMenu()");
        menu.clear();
        inflater.inflate(R.menu.mall_mycenter_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_settings == item.getItemId()) {
            RouterCenter.toMyCenterSetting();
        }
        return true;
    }

    @Override
    public void getUserInfor(UserInfor userInfor) {
        initMqtt(userInfor.getUserId());
        username.setText(userInfor.getUsername());
        userEmail.setText(userInfor.getEmail());
        if(userInfor.getUserState() == 1){
            authenticationText.setText("未认证");
            authenticationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(getActivity(),StudentAuthenticationActivity.class));
                }
            });
        } else {
            authenticationText.setText("已认证");
        }
        Glide.with(this)
                .load(userInfor.getHeadImag())
                .into(userHead);
        //userHead.setImageURI(Uri.parse(userInfor.getHeadImag()));
        mPresenter.getUserGoodsInfor(userInfor.getUserId());
        //ToastUtils.showLong(getActivity(), userInfor.toString());
    }


    @Override
    public void getUserGoodsInfor(UserAllInfor jsonObject) {

        L.d("getUserGoodsInfor--JSONObject--publishClassCount-" + jsonObject.getPublishClassCount());
        myPublish.setText(jsonObject.getPublishClassCount()+"");
        mySellNum.setText(jsonObject.getUserSellCount()+"");
        myBuyNum.setText(jsonObject.getUserBuyCount()+"");
        myBuyCar.setText(jsonObject.getUserCarCount()+"");
        //获得用户注册时间
        //Date createdDate = DateUtiles.stringToDate(jsonObject.getUserInfo().getRegisterDate(),"yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date createdDate = jsonObject.getUserInfo().getRegisterDate();
        long f = DateUtiles.getDaySub(DateUtiles.DateToString(createdDate,"yyyy-MM-dd HH:mm:ss"),DateUtiles.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //String signature = getContext().getResources().getString(R.string.user_signature);
        String signature = "加入校园拍拍"+f+"天，共赚"+jsonObject.getUserBuyAmount()+"元。";
        //String.format(signature, f,jsonObject.getUserBuyAmount());
        userSignature.setText(signature);
    }


    /**
     * 初始化Mqtt连接
     */
    public void initMqtt(int id){

        MQTTService.myTopic = "MESSAGE"+ id;
        MQTTService.clientId = "AndroidClient"+id;
        L.d("#######################mqtt开始连接");

        serviceConnection = new MyServiceConnection();
        serviceConnection.setIGetMessageCallBack(this);

        Intent intent = new Intent(getContext(), MQTTService.class);

        getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        /*MqttPushClient.getInstance(mqClientId).subscribe(kdTopic);
        MqttPushClient.getInstance(mqClientId).subscribe(kdTopic1);*/
    }

    @Override
    public void setMessage(String message) {
        L.d("Mqtt接收到的消息内容："+message);
        Gson gson = new Gson();
        NoticeMessage noticeMessage = gson.fromJson(message,NoticeMessage.class);
        PayNoticeMessage payNoticeMessage = gson.fromJson(noticeMessage.getTxno(),PayNoticeMessage.class);

        if ("3".equals(noticeMessage.getType())){ //支付通知
            Intent intent = new Intent(getContext(),MainActivity.class);
            intent.putExtra("homeWeb",1);
            intent.putExtra("isPaySuccess",payNoticeMessage.getState()+"");
            intent.putExtra("outTradeNo",payNoticeMessage.getOutTradeNo());
            intent.putExtra("tradeNo",payNoticeMessage.getTradeNo());
            intent.putExtra("totalAmount",payNoticeMessage.getTotalAmount());
            getContext().startActivity(intent);
        }

        new JavaScriptUtils(getActivity()).sendNotification1(noticeMessage.getTitle(),
                noticeMessage.getContent(),"",noticeMessage.getActiveCreatTime(),
                noticeMessage.getTxno());
    }

    public  String get4UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[1];
    }
}
