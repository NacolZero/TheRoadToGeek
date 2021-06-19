package com.nacol.TheRoadToGeek.common.utils;

import java.time.LocalDateTime;

public class SerialNoUtils {

    public static String generateSimpleSerialNo() {
        return LocalDateTime.now().getNano() + "";
    }

}
