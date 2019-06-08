package com.example.tome.module_shop_mall.bean;

/**
 * @Created by Bernie
 * @Date 2019/6/5 18:19
 **/
public class PayNoticeMessage {
    private String outTradeNo;
    private String tradeNo;
    private String totalAmount;
    private int state;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
