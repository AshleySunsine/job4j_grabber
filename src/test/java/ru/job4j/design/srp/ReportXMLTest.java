package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportXMLTest {
    @Test
    public void whenOldGenerated() {
        /*
        Тут кошмар с этими форматами дат.
         */
        MemStore store = new MemStore();
        Report engine = new ReportXML(store);
        Calendar now1 = new GregorianCalendar(2017, 0 ,25);
        Calendar now2 = new GregorianCalendar(2018, 0 ,15);
        Employee worker = new Employee("Ivan", now1, now2, 100);
        store.add(worker);
        DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss Z:Z");
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<employes>")
                .append("   <employesList name=\\\"Ivan\\\" hired=\\\"")
                .append(df.format(worker.getHired().getTime()))
                .append(" fired=\\")
                .append(df.format(worker.getFired().getTime()))
                .append(" salary=\\\"100.0\\\"/>")
                .append(System.lineSeparator())
                .append("</employes>");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}