package com.urise.webapp.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by viktoriyasidenko on 3/7/17.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{

    @Override
    public LocalDate unmarshal(String str) throws Exception {
        return LocalDate.parse(str);
    }

    @Override
    public String marshal(LocalDate ld) throws Exception {
        return ld.toString();
    }
}
