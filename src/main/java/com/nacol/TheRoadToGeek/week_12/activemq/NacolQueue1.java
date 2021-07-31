package com.nacol.TheRoadToGeek.week_12.activemq;

import static com.nacol.TheRoadToGeek.week_12.activemq.DestinationFactory.NACOL_QUEUE;

public class NacolQueue1 {

    public static void main(String[] args) {
        //绑定监听
        MQUtils.bindListener(NACOL_QUEUE.val, MQUtils.logListener());
    }

}
