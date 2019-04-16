package com.example.tome.module_shop_mall.bean;

import java.util.Date;

/**
 * @Created by Bernie
 * @Date 2019/4/16 8:56
 **/
public class CommentList {
    private int commentId;
    private int orderId;
    private int commentPerId;
    private int commentedPerId;
    private int serviceNum;
    private int descriptionNum;
    private int logisticsNum;
    private int damageNum;
    private Date commentCreatedTime;
    private String commentExpand1;
    private String commentExpand2;
    private String commentExpand3;
    private String commentExpand4;
    private String comment;
    private String username;
    private String orderNum;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCommentPerId() {
        return commentPerId;
    }

    public void setCommentPerId(int commentPerId) {
        this.commentPerId = commentPerId;
    }

    public int getCommentedPerId() {
        return commentedPerId;
    }

    public void setCommentedPerId(int commentedPerId) {
        this.commentedPerId = commentedPerId;
    }

    public int getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(int serviceNum) {
        this.serviceNum = serviceNum;
    }

    public int getDescriptionNum() {
        return descriptionNum;
    }

    public void setDescriptionNum(int descriptionNum) {
        this.descriptionNum = descriptionNum;
    }

    public int getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(int logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public int getDamageNum() {
        return damageNum;
    }

    public void setDamageNum(int damageNum) {
        this.damageNum = damageNum;
    }

    public Date getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(Date commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public String getCommentExpand1() {
        return commentExpand1;
    }

    public void setCommentExpand1(String commentExpand1) {
        this.commentExpand1 = commentExpand1;
    }

    public String getCommentExpand2() {
        return commentExpand2;
    }

    public void setCommentExpand2(String commentExpand2) {
        this.commentExpand2 = commentExpand2;
    }

    public String getCommentExpand3() {
        return commentExpand3;
    }

    public void setCommentExpand3(String commentExpand3) {
        this.commentExpand3 = commentExpand3;
    }

    public String getCommentExpand4() {
        return commentExpand4;
    }

    public void setCommentExpand4(String commentExpand4) {
        this.commentExpand4 = commentExpand4;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
