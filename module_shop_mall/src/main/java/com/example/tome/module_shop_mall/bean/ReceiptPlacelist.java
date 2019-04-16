package com.example.tome.module_shop_mall.bean;

/**
 * @Created by Bernie
 * @Date 2019/4/16 9:00
 **/
public class ReceiptPlacelist {
    private int receiptId;
    private int userId;
    private String addressName;
    private int addressState;
    private String addressExpand1;
    private String addressExpand2;

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getAddressState() {
        return addressState;
    }

    public void setAddressState(int addressState) {
        this.addressState = addressState;
    }

    public String getAddressExpand1() {
        return addressExpand1;
    }

    public void setAddressExpand1(String addressExpand1) {
        this.addressExpand1 = addressExpand1;
    }

    public String getAddressExpand2() {
        return addressExpand2;
    }

    public void setAddressExpand2(String addressExpand2) {
        this.addressExpand2 = addressExpand2;
    }
}
