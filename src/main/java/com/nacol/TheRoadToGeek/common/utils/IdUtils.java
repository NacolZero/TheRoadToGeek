package com.nacol.TheRoadToGeek.common.utils;

import java.util.UUID;

public class IdUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(generateUUID());
    }
}
