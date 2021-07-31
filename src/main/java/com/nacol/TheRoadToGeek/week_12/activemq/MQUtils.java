package com.nacol.TheRoadToGeek.week_12.activemq;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/31
 * @Description 接受者工具
 */
@Log4j2
public class MQUtils {

    private static Session session;
    private static ActiveMQConnection conn;
    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/7/31
     * @Description 创建监听者
     */
    public static void bindListener(Destination destination, MessageListener messageListener) {
        try {
            initSession();
            //(session + destination) → consumer
            MessageConsumer messageConsumer =session.createConsumer(destination);
            //consumer bind listener
            messageConsumer.setMessageListener(messageListener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/7/31
     * @Description 创建 Session （线程不安全）
     */
    private static void initSession() {
        if (session != null) {
            return;
        }
        try {
            //声明连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            //连接工厂 → 连接
            conn = (ActiveMQConnection)factory.createConnection();
            conn.start();
            //连接 → Session
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/7/31
     * @Description 日记监听者
     */
    public static MessageListener logListener() {
        return new MessageListener() {
            @Override
            public void onMessage(Message message) {
                log.info("listener recieve mesaage : {}", message);
            }
        };
    }

    public static void pub(Destination destination) throws JMSException {
        initSession();
        MessageProducer producer = session.createProducer(destination);
        int index = 0;
        while (index++< 100) {
            TextMessage message = session.createTextMessage("吃了" + index + "个粑粑.");
            producer.send(message);
        }
    }

    public static void finish() throws JMSException {
        conn.close();
        session.close();
    }
}
