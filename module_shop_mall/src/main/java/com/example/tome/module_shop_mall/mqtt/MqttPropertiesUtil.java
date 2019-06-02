package com.example.tome.module_shop_mall.mqtt;

/**
 * @Created by Bernie
 * @Date 2019/5/30 22:57
 **/
public class MqttPropertiesUtil {
    static String MQTT_HOST;//MQTT服务器地址和端口号
    static String MQTT_CLIENTID;//MQTT ClientID
    static String MQTT_USER_NAME;//MQTT 用户名
    static String MQTT_PASSWORD;//MQTT 用户密码
    static int MQTT_TIMEOUT; //MQTT超时时间
    static int MQTT_KEEP_ALIVE;//MQTT keepalive
    private static String MQTT_TOPIC;// MQTT 主题

    static {
        try {
            MQTT_HOST = "tcp://localhost:1883";
            MQTT_CLIENTID = "AndroidClient";
            MQTT_USER_NAME = "admin";
            MQTT_PASSWORD = "admin";
            MQTT_TIMEOUT = 10000;
            MQTT_KEEP_ALIVE = 60;
            MQTT_TOPIC = "test";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
