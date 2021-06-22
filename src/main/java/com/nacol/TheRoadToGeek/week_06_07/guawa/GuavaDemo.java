package com.nacol.TheRoadToGeek.week_06_07.guawa;

import com.google.common.base.Joiner;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Random;

public class GuavaDemo {

    static EventBus bus = new EventBus();
    static {
        // 订阅方法所在类，要注入到 bus 中
        bus.register(new GuavaDemo());
    }

    public static void main(String[] args) {
        //---------------------- Collections ----------------------
        List<Integer> list = Lists.newArrayList(1,1,2,1,2,3,4,5);

        //分片
        System.out.println("-------------- 分片 --------------");
        List<List<Integer>> partList = Lists.partition(list, 2);
        partList.forEach(o->System.out.println(o));

        //笛卡尔积
        System.out.println("-------------- 笛卡尔积 --------------");
        List<List<Integer>> lists = Lists.newArrayList(
                Lists.newArrayList(1,2),
                Lists.newArrayList(3,4)
//                Lists.newArrayList(5,6,3,4),
//                Lists.newArrayList(6,7,3,4),
//                Lists.newArrayList(910,1,3,4)
        );
        System.out.println(Lists.cartesianProduct(lists));

        //多值 map
        System.out.println("-------------- 多值 map --------------");
        Multimap<String, Integer> mulMap = ArrayListMultimap.create();
        list.forEach(num -> mulMap.put(num + "", new Random().nextInt(100)));
        mulMap.forEach((k, v)->System.out.println(k + " : " + v));

        //双向 map
        System.out.println("-------------- 双向 map --------------");
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("nacol", 19);
        biMap.put("gaga", 20);
        //不能有重复 value
//        biMap.put("lueluelue", 20);
        System.out.println(biMap.get("nacol").intValue());
        System.out.println(biMap.inverse().get(19));

        //---------------------- String ----------------------
        //join
        System.out.println("-------------- join --------------");
        String joinResult = Joiner.on(" + ").join(list);
        System.out.println(joinResult);

        //---------------------- Event Bus ----------------------
        System.out.println("--------------  Event Bus --------------");
        bus.post(new EventMsg("To Be Geek ^@^"));
    }


    @Data
    @AllArgsConstructor
    public static class EventMsg {
        private String msg;
    }

    @Subscribe
    public void consume(EventMsg eventMsg) {
        System.out.println(eventMsg);
    }
}
