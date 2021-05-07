package com.nacol.TheRoadToGeek.jvm;

import java.time.LocalDateTime;
import java.util.Random;


public class GCLogAnalysis {

    static Random rdm = new Random();

    public static void main(String[] args) {
        long endTime = System.currentTimeMillis() + 1000;
        int cacheLenght = 2000;
        Object[] cacheArray = new Object[cacheLenght];
        while (System.currentTimeMillis() < endTime) {
            int max = rdm.nextInt(4000);
            Object[] result = generateArray(max);
            int prize = rdm.nextInt(cacheLenght * 2);
            if (prize < cacheLenght) {
                cacheArray[prize] = result;
            }
        }
    }

    private static Object[] generateArray(int max) {
        int selectNum = rdm.nextInt(4);
        Object[] result = new Object[]{};
        switch (selectNum) {
            case 0:
                result = new Integer[max];
                break;
            case 1:
                result = new Double[max];
                break;
            case 2:
                result = new Long[max];
                break;
            default:
                String[] array = new String[max];
                for(int i = 0; i < max; i++){
                    array[i] = i + "";
                }
                result = array;
        }
        return result;
    }

}
