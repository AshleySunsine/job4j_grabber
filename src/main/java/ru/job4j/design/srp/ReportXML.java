package ru.job4j.design.srp;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML  implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> employeesList = new ArrayList<>();
        try {
        JAXBContext context = JAXBContext.newInstance(Employes.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            for (Employee employee : store.findBy(filter)) {
                employeesList.add(employee);
            }
            Employes employes = new Employes(employeesList);
            marshaller.marshal(employes, writer);
            text.append(writer);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text);
        return text.toString();
    }

    @XmlRootElement(name = "employes")
    private static class Employes {
        @XmlElement
        private List<Employee> employesList = new ArrayList<>();

        private Employes() {
            //for JAXB
        }

        public Employes(List<Employee> employesList) {
            this.employesList = employesList;
        }
    }
}

