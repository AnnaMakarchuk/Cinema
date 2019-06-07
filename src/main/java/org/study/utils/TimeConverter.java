package org.study.utils;

import java.time.LocalTime;

public class TimeConverter {

    public static java.sql.Time convertLocalTimeToSQLTime(LocalTime time) {
        return java.sql.Time.valueOf(time.plusHours(2));
    }

    public static LocalTime convertSQLTimeToLocalTime(java.sql.Time time) {
        return time.toLocalTime().minusHours(2);
    }
}
