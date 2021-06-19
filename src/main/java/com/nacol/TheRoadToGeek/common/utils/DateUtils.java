package com.nacol.TheRoadToGeek.common.utils;

import java.time.LocalDateTime;

public class DateUtils {

    public static long getCurrentNano() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(getCurrentNano());
    }
}
