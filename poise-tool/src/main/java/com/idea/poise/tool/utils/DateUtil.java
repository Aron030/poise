package com.idea.poise.tool.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: aron
 * @Date: 2022/06/29/21:14
 * @Description: 日期工具类
 */
public class DateUtil {
    public static final String PATTERN_DATETIME_MS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_MINI = "yyyyMMddHHmmss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    public static Date now() {
        return new Date();
    }


    public static String formatNow() {
        return format(getDateTime(),PATTERN_DATETIME_MS);
    }

    public static String format(TemporalAccessor temporal, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return formatter.format(temporal);
    }

    /**
     * 时间转 Instant
     *
     * @param dateTime 时间
     * @return Instant
     */
    public static Instant toInstant(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }


    /**
     * 转换成 date
     *
     * @param dateTime LocalDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(DateUtil.toInstant(dateTime));
    }

    /**
     * 转换成 date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static Date parse(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static Duration between(Date startDate,Date endDate){
        return Duration.between(startDate.toInstant(),endDate.toInstant());
    }

}
