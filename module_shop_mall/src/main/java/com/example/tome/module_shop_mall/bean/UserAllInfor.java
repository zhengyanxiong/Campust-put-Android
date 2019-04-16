package com.example.tome.module_shop_mall.bean;

import java.util.List;

/**
 * @Created by Bernie
 * @Date 2019/4/16 9:02
 **/
public class UserAllInfor {
    private List<CommentList> commentList;
    private UserInfor userInfo;
    private int userSellCount;
    private List<GoodsList> goodsList;
    private int goodsUpCount;
    private int publishClassCount;
    private int userBuyCount;
    private List<ReceiptPlacelist> receiptPlacelist;
    private int publishGoodsCount;
    private List<CommentedList> commentedList;
    private int goodsOnCount;
    private int goodsSellCount;
    private int goodsActiveCount;

    public List<CommentList> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentList> commentList) {
        this.commentList = commentList;
    }

    public UserInfor getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfor userInfo) {
        this.userInfo = userInfo;
    }

    public int getUserSellCount() {
        return userSellCount;
    }

    public void setUserSellCount(int userSellCount) {
        this.userSellCount = userSellCount;
    }

    public List<GoodsList> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsList> goodsList) {
        this.goodsList = goodsList;
    }

    public int getGoodsUpCount() {
        return goodsUpCount;
    }

    public void setGoodsUpCount(int goodsUpCount) {
        this.goodsUpCount = goodsUpCount;
    }

    public int getPublishClassCount() {
        return publishClassCount;
    }

    public void setPublishClassCount(int publishClassCount) {
        this.publishClassCount = publishClassCount;
    }

    public int getUserBuyCount() {
        return userBuyCount;
    }

    public void setUserBuyCount(int userBuyCount) {
        this.userBuyCount = userBuyCount;
    }

    public List<ReceiptPlacelist> getReceiptPlacelist() {
        return receiptPlacelist;
    }

    public void setReceiptPlacelist(List<ReceiptPlacelist> receiptPlacelist) {
        this.receiptPlacelist = receiptPlacelist;
    }

    public int getPublishGoodsCount() {
        return publishGoodsCount;
    }

    public void setPublishGoodsCount(int publishGoodsCount) {
        this.publishGoodsCount = publishGoodsCount;
    }

    public List<CommentedList> getCommentedList() {
        return commentedList;
    }

    public void setCommentedList(List<CommentedList> commentedList) {
        this.commentedList = commentedList;
    }

    public int getGoodsOnCount() {
        return goodsOnCount;
    }

    public void setGoodsOnCount(int goodsOnCount) {
        this.goodsOnCount = goodsOnCount;
    }

    public int getGoodsSellCount() {
        return goodsSellCount;
    }

    public void setGoodsSellCount(int goodsSellCount) {
        this.goodsSellCount = goodsSellCount;
    }

    public int getGoodsActiveCount() {
        return goodsActiveCount;
    }

    public void setGoodsActiveCount(int goodsActiveCount) {
        this.goodsActiveCount = goodsActiveCount;
    }
}
