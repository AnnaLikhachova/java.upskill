package com.likhachova.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {

    public static Date convertToDatabaseColumn(LocalDate entityValue) {
        return Date.valueOf(entityValue);
    }

    public static LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue.toLocalDate();
    }
}
