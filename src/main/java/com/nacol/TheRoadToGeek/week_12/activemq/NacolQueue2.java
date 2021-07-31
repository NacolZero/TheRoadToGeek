package com.nacol.TheRoadToGeek.week_12.activemq;

import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Destination;

import static com.nacol.TheRoadToGeek.week_12.activemq.DestinationFactory.NACOL_QUEUE;

public class NacolQueue2 {

    public static void main(String[] args) {
        //绑定监听
        MQUtils.bindListener(NACOL_QUEUE.val, MQUtils.logListener());
    }

}
