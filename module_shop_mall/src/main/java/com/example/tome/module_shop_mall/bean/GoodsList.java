package com.example.tome.module_shop_mall.bean;

import java.util.Date;

/**
 * @Created by Bernie
 * @Date 2019/4/16 9:00
 **/
public class GoodsList {
    private int goodsId;
    private int userId;
    private int goodClassNum;
    private String goodsNum;
    private String goodsName;
    private int goodsPrice;
    private boolean isNegotiable;
    private Date goodsPublishTime;
    private Date goodsUpdatedTime;
    private String goodsActivePrice;
    private int goodsStock;
    private int goodsState;
    private String goodsDescription;
    private String goodClassName;
    private String goodImge;
    private String username;
    private String goodImages;
    private String goodsPubData;
    private String goodsUpdData;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodClassNum() {
        return goodClassNum;
    }

    public void setGoodClassNum(int goodClassNum) {
        this.goodClassNum = goodClassNum;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public boolean isNegotiable() {
        return isNegotiable;
    }

    public void setNegotiable(boolean negotiable) {
        isNegotiable = negotiable;
    }

    public Date getGoodsPublishTime() {
        return goodsPublishTime;
    }

    public void setGoodsPublishTime(Date goodsPublishTime) {
        this.goodsPublishTime = goodsPublishTime;
    }

    public Date getGoodsUpdatedTime() {
        return goodsUpdatedTime;
    }

    public void setGoodsUpdatedTime(Date goodsUpdatedTime) {
        this.goodsUpdatedTime = goodsUpdatedTime;
    }

    public String getGoodsActivePrice() {
        return goodsActivePrice;
    }

    public void setGoodsActivePrice(String goodsActivePrice) {
        this.goodsActivePrice = goodsActivePrice;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodClassName() {
        return goodClassName;
    }

    public void setGoodClassName(String goodClassName) {
        this.goodClassName = goodClassName;
    }

    public String getGoodImge() {
        return goodImge;
    }

    public void setGoodImge(String goodImge) {
        this.goodImge = goodImge;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodImages() {
        return goodImages;
    }

    public void setGoodImages(String goodImages) {
        this.goodImages = goodImages;
    }

    public String getGoodsPubData() {
        return goodsPubData;
    }

    public void setGoodsPubData(String goodsPubData) {
        this.goodsPubData = goodsPubData;
    }

    public String getGoodsUpdData() {
        return goodsUpdData;
    }

    public void setGoodsUpdData(String goodsUpdData) {
        this.goodsUpdData = goodsUpdData;
    }
}
