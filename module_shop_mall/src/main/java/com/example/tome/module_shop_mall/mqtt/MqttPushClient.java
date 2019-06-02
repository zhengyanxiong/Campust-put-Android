package com.example.tome.module_shop_mall.mqtt;

import com.example.tome.core.util.L;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


/**
 * @Created by Bernie
 * @Date 2019/5/30 22:58
 **/
public class MqttPushClient {

    private MqttClient client;

    private MqttPropertiesUtil mPropertiesUtil;

    private static volatile MqttPushClient mqttPushClient = null;

    public static MqttPushClient getInstance(String mqClientId){

        if(null == mqttPushClient){
            synchronized (MqttPushClient.class){
                if(null == mqttPushClient){
                    mqttPushClient = new MqttPushClient(mqClientId);
                }
            }

        }
        return mqttPushClient;
    }

    /**
     * @Description //TODO MQTT连接入口
     * @Date 11:14 2019/3/9
     * @Param []
     * @return
     **/
    private MqttPushClient(String mqClientId) {
        connect(mqClientId);
    }

    /**
     * @Description //TODO 连接MQTT服务器
     * @Date 11:07 2019/3/9
     * @Param 无参数
     * @return void
     **/
    private void connect(String mqClientId){
        try {
            client = new MqttClient(MqttPropertiesUtil.MQTT_HOST, mqClientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName(MqttPropertiesUtil.MQTT_USER_NAME);
            options.setPassword(MqttPropertiesUtil.MQTT_PASSWORD.toCharArray());
            options.setConnectionTimeout(MqttPropertiesUtil.MQTT_TIMEOUT);
            options.setKeepAliveInterval(MqttPropertiesUtil.MQTT_KEEP_ALIVE);
            try {
                client.setCallback(new PushCallback());
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布，默认qos为0，非持久化
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic,PushPayload pushMessage){
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos,boolean retained,String topic,PushPayload pushMessage){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.toString().getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if(null == mTopic){
            L.d("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     * @param topic
     */
    public void subscribe(String topic){
        subscribe(topic,0);
    }

    /**
     * 订阅某个主题
     * @param topic
     * @param qos
     */
    public void subscribe(String topic,int qos){
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String kdTopic = "good";
        String mqClientId = "good";
        PushPayload pushMessage = PushPayload.getPushPayloadBuider().setMobile("15345715326")
                .setContent("designModel")
                .bulid();
        MqttPushClient.getInstance(mqClientId).publish(0, false, kdTopic, pushMessage);
    }
}
