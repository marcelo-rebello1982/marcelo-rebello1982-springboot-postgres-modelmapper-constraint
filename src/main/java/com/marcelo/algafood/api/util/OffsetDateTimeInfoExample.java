package com.marcelo.algafood.api.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;


public class OffsetDateTimeInfoExample {


    public static void main(String[] args) {

        OffsetDateTime offsetDT = OffsetDateTime.now();

        System.out.println("OffsetDateTime   : " + offsetDT);
        System.out.println("DayOfMonth       : " + offsetDT.getDayOfMonth());
        System.out.println("MonthValue       : " + offsetDT.getMonthValue());
        System.out.println("Year             : " + offsetDT.getYear());
        System.out.println("Hour             : " + offsetDT.getHour());
        System.out.println("Minute           : " + offsetDT.getMinute());
        System.out.println("Second           : " + offsetDT.getSecond());
        System.out.println("Nano             : " + offsetDT.getNano());
        System.out.println("DayOfWeek        : " + offsetDT.getDayOfWeek());
        System.out.println("Month            : " + offsetDT.getMonth());
        System.out.println("DayOfYear        : " + offsetDT.getDayOfYear());
        System.out.println("DAY_OF_MONTH     : " + offsetDT.get(ChronoField.DAY_OF_MONTH));
        System.out.println("MONTH_OF_YEAR    : " + offsetDT.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("YEAR             : " + offsetDT.get(ChronoField.YEAR));
        System.out.println("HOUR_OF_DAY      : " + offsetDT.get(ChronoField.HOUR_OF_DAY));
        System.out.println("MINUTE_OF_HOUR   : " + offsetDT.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("SECOND_OF_MINUTE : " + offsetDT.get(ChronoField.SECOND_OF_MINUTE));
        System.out.println("MINUTE_OF_DAY    : " + offsetDT.getLong(ChronoField.MINUTE_OF_DAY));
        System.out.println("SECOND_OF_DAY    : " + offsetDT.getLong(ChronoField.SECOND_OF_DAY));
        System.out.println("Offset           : " + offsetDT.getOffset());
        System.out.println("toEpochSecond()  : " + offsetDT.toEpochSecond());
        System.out.println("toInstant()      : " + offsetDT.toInstant());
        System.out.println("toLocalDate()    : " + offsetDT.toLocalDate());
        System.out.println("toLocalDateTime(): " + offsetDT.toLocalDateTime());
        System.out.println("toLocalTime()    : " + offsetDT.toLocalTime());
        System.out.println("toZonedDateTime(): " + offsetDT.toZonedDateTime());

    }

}