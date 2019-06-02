package com.example.tome.module_shop_mall.mqtt;

/**
 * @Created by Bernie
 * @Date 2019/5/30 23:10
 **/
public class test {
    public static void main(String[] args){
        String kdTopic = "good";
        String kdTopic1 = "MESSAGE3";
        String mqClientId = "AndroidClient4";

        MqttPushClient.getInstance(mqClientId).subscribe(kdTopic);
        MqttPushClient.getInstance(mqClientId).subscribe(kdTopic1);
    }
}
