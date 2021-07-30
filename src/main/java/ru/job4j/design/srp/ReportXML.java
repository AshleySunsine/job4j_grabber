package ru.job4j.design.srp;

import javax.xml.bind.*;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML  implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        try {
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            for (Employee employee : store.findBy(filter)) {
                marshaller.marshal(employee, writer);
                text.append(writer);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text);
        return text.toString();
    }
}
