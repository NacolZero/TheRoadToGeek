package com.nacol.TheRoadToGeek.week_12.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Destination;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/31
 * @Description 队列工厂
 */
public enum DestinationFactory {

    NACOL_TOPIC(new ActiveMQTopic("nacol.topic")),
    NACOL_QUEUE(new ActiveMQQueue("nacol.queue"));

    Destination val;

    DestinationFactory(Destination val) {
        this.val = val;
    }


}
