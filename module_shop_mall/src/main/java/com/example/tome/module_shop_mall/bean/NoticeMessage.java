package com.example.tome.module_shop_mall.bean;

/**
 * @Created by Bernie
 * @Date 2019/6/2 17:57
 **/
public class NoticeMessage {
    private String activeCreatTime;
    private int badge;
    private String content;
    private String sound;
    private String title;
    private String type;
    private String txno;
    public void setActiveCreatTime(String activeCreatTime) {
        this.activeCreatTime = activeCreatTime;
    }
    public String getActiveCreatTime() {
        return activeCreatTime;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }
    public int getBadge() {
        return badge;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
    public String getSound() {
        return sound;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public String getTxno() {
        return txno;
    }

    public void setTxno(String txno) {
        this.txno = txno;
    }
}
