package com.example.tome.module_shop_mall.mqtt;


/**
 * @Created by Bernie
 * @Date 2019/5/30 23:03
 **/

public class PushPayload {
    //推送类型
    private String type;
    //推送对象
    private String mobile;
    //标题
    private String title;
    //内容
    private String content;
    //数量
    private Integer badge = 1;
    //铃声
    private String sound;
    //站场
    private String station;
    //轨道
    private String tracks;
    //设备
    private String device;
    // 数据
    private Object data;
    //二级对象
    private String mode;
    //铁鞋编号
    private String txno;
    //车号
    private String trainnumber;
    //活动日期
    private String activeDate;
    //活动创建日期
    private String activeCreatTime;
    //活动图片
    private String activeImage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTracks() {
        return tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTxno() {
        return txno;
    }

    public void setTxno(String txno) {
        this.txno = txno;
    }

    public String getTrainnumber() {
        return trainnumber;
    }

    public void setTrainnumber(String trainnumber) {
        this.trainnumber = trainnumber;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getActiveCreatTime() {
        return activeCreatTime;
    }

    public void setActiveCreatTime(String activeCreatTime) {
        this.activeCreatTime = activeCreatTime;
    }

    public String getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(String activeImage) {
        this.activeImage = activeImage;
    }

    private PushPayload(String type, String mobile, String title, String content, Integer badge
            , String sound, String station, String tracks, String device, Object data
            , String mode, String txno, String trainnumber, String activeDate, String activeImage, String activeCreatTime) {
        this.type = type;
        this.mobile = mobile;
        this.title = title;
        this.content = content;
        this.badge = badge;
        this.sound = sound;
        this.station = station;
        this.tracks = tracks;
        this.device = device;
        this.data = data;
        this.mode = mode;
        this.txno = txno;
        this.trainnumber = trainnumber;
        this.activeDate = activeDate;
        this.activeImage = activeImage;
        this.activeCreatTime = activeCreatTime;
    }

    public static class Builder {
        //推送类型
        private String type;
        //推送对象
        private String mobile;
        //标题
        private String title;
        //内容
        private String content;
        //数量
        private Integer badge = 1;
        //铃声
        private String sound = "default";
        //站场
        private String station;
        //轨道
        private String tracks;
        //设备
        private String device;
        // 数据
        private Object data;
        //二级对象
        private String mode;
        //铁鞋编号
        private String txno;
        //车号
        private String trainnumber;
        //活动时间
        private String activeDate;
        //活动创建时间
        private String activeCreatTime;
        //活动图片
        private String activeImage;

        public Builder setActiveDate(String activeDate) {
            this.activeDate = activeDate;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setBadge(Integer badge) {
            this.badge = badge;
            return this;
        }

        public Builder setSound(String sound) {
            this.sound = sound;
            return this;
        }

        public Builder setStation(String station) {
            this.station = station;
            return this;
        }

        public Builder setTracks(String tracks) {
            this.tracks = tracks;
            return this;
        }

        public Builder setDevice(String device) {
            this.device = device;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setMode(String mode) {
            this.mode = mode;
            return this;
        }

        public Builder setTxno(String txno) {
            this.txno = txno;
            return this;
        }

        public Builder setTrainnumber(String trainnumber) {
            this.trainnumber = trainnumber;
            return this;
        }

        public Builder setActiveCreatTime(String activeCreatTime) {
            this.activeCreatTime = activeCreatTime;
            return this;
        }

        public Builder setActiveImage(String activeImage) {
            this.activeImage = activeImage;
            return this;
        }

        public PushPayload bulid() {
            return new PushPayload(type, mobile, title, content, badge, sound, station, tracks,
                    device, data,mode,txno,trainnumber,activeDate,activeImage,activeCreatTime);
        }

    }

    public static Builder getPushPayloadBuider() {
        return new Builder();
    }


}
