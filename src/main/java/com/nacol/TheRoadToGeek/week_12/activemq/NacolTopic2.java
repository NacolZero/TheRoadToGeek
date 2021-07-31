package com.nacol.TheRoadToGeek.week_12.activemq;

import lombok.extern.log4j.Log4j2;

import static com.nacol.TheRoadToGeek.week_12.activemq.DestinationFactory.NACOL_TOPIC;


@Log4j2
public class NacolTopic2 {

    public static void main(String[] args) {
        //绑定监听
        MQUtils.bindListener(NACOL_TOPIC.val, MQUtils.logListener());
    }

}
