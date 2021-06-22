package com.nacol.TheRoadToGeek.week_06_07.lambda;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaDemo {

    public static void main(String[] args) {
        //使用 lambda 表达式作为接口实现类
        //前提：接口类中仅仅有一个未实现的方法(非 object 方法)；
        //比方说 Comparator
        ISender sender = s->System.out.println(s);
        sender.sendMsg("hahah");

        //封装后使用方式
        sendMsg("lueluelue", System.out::println);

        // :: 方式代替
        List<Runner> runners = Arrays.asList(new Runner());
        runners.stream().forEach(Runner::say);

        //lambda 作为接口实现类
        Comparator<Runner> comparator1 = (runner1,runner2) -> runner1.getSpeed() - runner2.getSpeed();
        //使用 CompareToBuilder 实现复杂比较
        Comparator<Runner> comparator2 = (runner1,runner2) ->
            new CompareToBuilder()
                .append(runner1.getSpeed(), runner2.getSpeed())
                .build();


        /**
         * 关于 lambda 代替接口的实现类的一点思考：
         * 这种方式的确可以少些不少代码，
         * 但是也同时带来了一些问题：
         * 1. 高耦合：在某些场景下，调用类和实现类做着完全不同的事情，但是却把不同业务耦合在一个类中，从设计上来说，是 “反模式”；
         * 2. 快餐式的使用：在 A 类中的实现，却难以在 B 类中复用，如果强行使用却增加了无不相关 A 和 B 的耦合。又违背了高内聚低耦合。
         *      因此 lambda 的实现通常作为快餐使用方式（仅仅一个地方使用，不会复用）
         *
         * 比方说像 Comparator 这类比较轻的接口，排序规则可视为当前类业务的一部分，这时候用 lambda 不仅少些代码，还高内聚。
         * 而且排序这样的业务通常也不会被其它业务复用。
         *
         * 因此 lambda 代替接口类的实现的场景通常是比较 “轻” 的接口实现，实现的功能可作为是和当前模块、类的一部分。
         * 如果 lambda 实现的接口比较 “重” ，最好还是抽离出去专门作为一个实现类比较合理。
         *
         * 如果为了用 lambda 而用 lamda 代码看似简单，却会带来更多设计问题。我就暂不为了作业而去强行改我的代码了。
         */




        /**
         * 可使用 Function、Consumer、Supplier、Predicate 代替 lambda，
         * 对于某些抽象的接口可传入不同的 Function、Consumer、Supplier、Predicate 实现，从而达到面向抽象编程。
         */
        //函数：有参数和返回
        Function<Integer, String> function = num->{
            switch (num) {
                case 0:
                    return "略略略";
                case 1:
                    return "啦啦啦";
                default:
                    return "嘎嘎嘎";
            }
        };

        //消费者：吃掉参数，没有返回
        Consumer<String> consumer = t -> System.out.println(t);

        //生产者: 不喂参数，有返回
        Supplier<String> supplier = () -> "生产";

        //条件
        Predicate<Integer> predicate = num -> num == 1;


    }

    private static void sendMsg(String msg, ISender sender) {
        sender.sendMsg(msg);
    }
}
