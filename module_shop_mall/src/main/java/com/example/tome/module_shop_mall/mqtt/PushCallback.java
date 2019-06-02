package com.example.tome.module_shop_mall.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Created by Bernie
 * @Date 2019/5/30 23:01
 **/
public class PushCallback implements MqttCallback {

    public interface GetMqttMessage{
        void getMqttMessageArrived(String topic,String message);
    }

    GetMqttMessage getMqttMessage;

    public void setGetMqttMessage(GetMqttMessage getMqttMessage) {
        this.getMqttMessage = getMqttMessage;
    }

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        //发动完成后回调函数
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload()));

        getMqttMessage.getMqttMessageArrived(topic,new String(message.getPayload()));
    }
}
