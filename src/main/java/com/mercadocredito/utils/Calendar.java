package com.mercadocredito.utils;
/**
 * Clase Calendar
 *
 * Contiene metodos para obtener elementos relacionados con fechas y horas
 *
 * @author Robert Carmona
 * @version 1.0
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar {

    /**
     * @return la fecha y hora actual en formato ISO 8601
     */
    public static String getDateTimeNowISO8601(){
        Date now = new Date();
        SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return isoDate.format(now);
    }
}
