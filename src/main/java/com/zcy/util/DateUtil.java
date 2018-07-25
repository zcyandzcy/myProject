package com.zcy.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final String DatePattern = "yyyy-MM-dd";
    private static final String TimePattern = "yyyy-MM-dd HH:mm:ss";
    private static final String TimestampPattern = "yyyyMMddHHmmss";

    private static final ZoneId zone = ZoneId.systemDefault();

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DatePattern);
    }

    public static SimpleDateFormat getDateTimeFormat() {
        return new SimpleDateFormat(TimePattern);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormat().format(date);
    }

    public static Date parseDate(String date) {
        try {
            return getDateFormat().parse(date);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("日期转换失败 date:" + date);
        }
    }

    public static Date parseDateTime(String dateTime) {
        try {
            return getDateTimeFormat().parse(dateTime);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("日期转换失败 dateTime:" + dateTime);
        }
    }

    public static Date getNowDate() {

        Date nowTime = new Date();
        String date = formatDate(nowTime);
        Date nowDate = parseDate(date);
        return nowDate;
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(TimestampPattern).format(new Date());
    }

    /**
     * 求两个日期相差多少天
     *
     * @param a
     * @param b
     * @return
     */
    public static int subDateOfDay(Date a, Date b) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(a);
        int na = ca.get(Calendar.DAY_OF_YEAR);


        Calendar cb = Calendar.getInstance();
        cb.setTime(b);
        int nb = cb.get(Calendar.DAY_OF_YEAR);

        return Math.abs(na - nb);
    }

    /**
     * 求两个日期相差的分钟数
     *
     * @param a
     * @param b
     * @return 正数：前者更大，时间更后面
     */
    public static int subDateOfMins(Date a, Date b) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(a);
        int na = ca.get(Calendar.MINUTE);


        Calendar cb = Calendar.getInstance();
        cb.setTime(b);
        int nb = cb.get(Calendar.MINUTE);

        return Math.abs(na - nb);
    }

    //java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    //java.time.LocalDateTime --> java.util.Date
    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    // 04. java.time.LocalDateTime --> java.util.Date
    public static Date LocalDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date LocalDate2Date(LocalDate localDate) {
        Instant instant = localDate.atTime(LocalTime.MIN).atZone(zone).toInstant();
        return Date.from(instant);
    }
}
