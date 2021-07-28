package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForBugalter implements Report {
    private Store store;

    public ReportForBugalter(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        SalaryModifier moder = new SalaryModding();
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
        .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double hrSalary = moder.mode(employee.getSalary());
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(hrSalary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
