package org.study.utils;

import java.time.LocalTime;

public class TimeConverter {

    public static java.sql.Time convertLocalTimeToSQLTime(LocalTime time) {
        return java.sql.Time.valueOf(time);
    }

    public static LocalTime convertSQLTimeToLocalTime(java.sql.Time time) {
        return time.toLocalTime();
    }
}
