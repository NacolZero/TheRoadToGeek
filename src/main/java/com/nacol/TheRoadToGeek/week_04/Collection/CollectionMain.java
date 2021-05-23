package com.nacol.TheRoadToGeek.week_04.Collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionMain {

    public static void main(String[] args) {
        synList();
    }



    public static void arrayListDemo() {
        //太简单太基础懒得写了，文字总结即可
        //写写的冲突：
        // 一方面是多个线程同时写一个角标造成的数据不统一，
        // 另一个方面是 add 虽然不修改 modCount ，但是一旦 add 造成数组扩容，则会修改 modecount，就会报错。
        //读写：
        //一方面: add 、get 的读写，add 不扩容的情况下可能会造成最简单的多线程读写不一致情况，如果扩容，还在多线程下连续 add 则报错。
        //另一方面： 使用 foreach 中 get ， foreach 会检查 modcount ，一旦改变则直接跑错。
        //remove、clrear、size、扩容 都会修改 modcount，就是为了不让多线程下使用，要出问题。
//        List<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    }

    public static void linkedListDemo() {
//        LinkedList
    }

    public static void synList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        List<Integer> synList = Collections.synchronizedList(list);
        List<Integer> orList = Collections.unmodifiableList(list);
        List<Integer> copyList = new CopyOnWriteArrayList<Integer>();
    }

}
