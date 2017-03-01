package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by viktoriyasidenko on 2/28/17.
 */
public class DateUtil {

    public static LocalDate of(int year, Month month){
        return LocalDate.of(year, month, 1);
    }
}
