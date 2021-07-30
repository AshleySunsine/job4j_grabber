package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportXMLTest {
    @Test
    public void whenOldGenerated() {
        /*
        Нужно согласовать форматы дат.
         */
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employee name=\"Ivan\" hired=\"")
                .append(now.getTime())
                .append("\"fired=\"")
                .append(now.getTime())
                .append("\"salary=\"100.0\"/>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}