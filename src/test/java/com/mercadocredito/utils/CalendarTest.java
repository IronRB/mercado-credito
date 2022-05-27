package com.mercadocredito.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class CalendarTest {

    @Test
    void getDateTimeNowISO8601() {
        Date now = new Date();
        SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd HH:mm'Z'");
        Assertions.assertEquals(isoDate.format(now),Calendar.getDateTimeNowISO8601());
    }
}