package com.nacol.TheRoadToGeek.week_06.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5,3,2,1,1,1,2,3,4,5,6);
        Comparator<Integer> descComp = (o1, o2)-> o2 - o1;
        Comparator<Integer> ascComp = (o1, o2)-> o2 - o1;

        //----------------------中间操作 | 选择和过滤----------------------------
        System.out.println("-------去重-------");
        print(numbers.stream().distinct());

        System.out.println("-------截断尾巴-------");
        print(numbers.stream().limit(2));

        System.out.println("-------跳过（砍掉脑壳）-------");
        print(numbers.stream().skip(1));

        //----------------------中间操作 | 映射----------------------------
        System.out.println("-------map-------");
        print(numbers.stream().map(o->o + "岁"));

        System.out.println("-------mapToDouble-------");
        numbers.stream().mapToDouble(o->o + 2).forEach(System.out::println);

        System.out.println("-------mapToLong-------");
        numbers.stream().mapToLong(o->o + 2).forEach(System.out::println);

        System.out.println("-------mapToInt-------");
        numbers.stream().mapToInt(o->o + 2).forEach(System.out::println);

        System.out.println("-------flatMap-------");
        print(numbers.stream().flatMap(o-> Arrays.asList(o, 998).stream()));

        //----------------------中间操作 | sorted----------------------------
        System.out.println("-------sorted-------");
        print(numbers.stream().sorted());

        System.out.println("-------sorted by Coparator-------");
        print(numbers.stream().sorted(descComp));

        //----------------------终止操作 | 查找与匹配----------------------------
        System.out.println("-------allMatch-------");
        System.out.println("是否全大于零：" + numbers.stream().allMatch(o -> o > 0));

        System.out.println("-------anyMatch-------");
        System.out.println("是否有大于零的数：" + numbers.stream().anyMatch(o -> o > 10));

        System.out.println("-------noneMatch-------");
        System.out.println("是否全都不大于零：" + numbers.stream().noneMatch(o -> o > 10));

        System.out.println("-------findFirst-------");
        System.out.println(numbers.stream().findFirst());

        System.out.println("-------findAny-------");
        System.out.println(numbers.stream().findAny());

        System.out.println("-------count-------");
        System.out.println(numbers.stream().count());

        System.out.println("-------max-------");
        System.out.println(numbers.stream().max(ascComp));

        System.out.println("-------min-------");
        System.out.println(numbers.stream().min(ascComp));

        //----------------------终止操作 | 规约----------------------------
        System.out.println("-------reduce-------");
        System.out.println(numbers.stream().reduce(Integer::sum));
        System.out.println(numbers.stream().reduce((o1, o2) -> o1 + o2));
        System.out.println(numbers.parallelStream().reduce((o1, o2) -> o1 * o2));

        //----------------------终止操作 | 搜集----------------------------
        numbers.stream().collect(Collectors.toList());
        numbers.stream().collect(Collectors.toSet());
        Map<Integer, Integer> intMap = numbers.stream()
                .collect(Collectors.toMap(o->o, o->o+1, (o1, o2)->o1));
        intMap.forEach((k, v)-> System.out.println(k + ":" + v));
        //----------------------终止操作 | 迭代----------------------------
        IntSummaryStatistics statistics = numbers.stream().mapToInt(num -> num).summaryStatistics();
        //statistics: 各种统计操作

        //----------------------其他----------------------------
        System.out.println("-------1出现的次数-------");
        System.out.println(Collections.frequency(numbers, 1));

        System.out.println("-------创建单个元素的数组-------");
        System.out.println(Collections.singletonList(1));

    }

    private static void print(Stream stream) {
        List<String> stringList = (List<String>) stream.map(o->o + "").collect(Collectors.toList());
        System.out.println(String.join(",", stringList));
    }

}
