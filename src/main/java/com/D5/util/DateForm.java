package com.D5.util;

import com.D5.domain.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class DateForm {
    public static List<Data> dateForm(List<Data> list) throws ParseException {
        for (Data e : list) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            e.setReceive_time((format.format(e.getReceive_time())));
        }
        return list;
    }
}
