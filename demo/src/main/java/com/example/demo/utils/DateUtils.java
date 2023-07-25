package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static java.sql.Date stringToSqlDate(String dataString) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date data;
        if (dataString != null && !dataString.isEmpty() && !dataString.isBlank()) {
            data = sdf.parse(dataString);
            java.sql.Date dataSql = new java.sql.Date(data.getTime());
            return dataSql;
        }
        return null;
    }
}
