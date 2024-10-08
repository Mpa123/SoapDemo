package com.zubak.demo.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Component
public class LocalDateAdapter extends XmlAdapter<XMLGregorianCalendar, LocalDate> {

    @Override
    public LocalDate unmarshal(XMLGregorianCalendar v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    @Override
    public XMLGregorianCalendar marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null;
        }
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(v.atStartOfDay(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}
