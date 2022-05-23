package com.mercadocredito.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar {

    public static String getDateTimeNowISO8601(){
        Date now = new Date();
        SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return isoDate.format(now);
    }
}
