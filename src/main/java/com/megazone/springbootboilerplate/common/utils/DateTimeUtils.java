package com.megazone.springbootboilerplate.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {

    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);

    public static String nowFormat() {
        return format(LocalDateTime.now());
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_DATE_TIME_FORMATTER);
    }
}
