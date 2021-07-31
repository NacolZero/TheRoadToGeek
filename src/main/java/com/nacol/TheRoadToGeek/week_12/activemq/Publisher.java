package com.nacol.TheRoadToGeek.week_12.activemq;

import javax.jms.JMSException;

import static com.nacol.TheRoadToGeek.week_12.activemq.DestinationFactory.NACOL_QUEUE;
import static com.nacol.TheRoadToGeek.week_12.activemq.DestinationFactory.NACOL_TOPIC;

public class Publisher {

    public static void main(String[] args) throws JMSException {
        MQUtils.pub(NACOL_QUEUE.val);
        MQUtils.pub(NACOL_TOPIC.val);
    }

}
