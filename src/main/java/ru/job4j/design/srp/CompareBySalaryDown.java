package ru.job4j.design.srp;

import java.util.Comparator;

public class CompareBySalaryDown implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getSalary() == o2.getSalary()) {
            return 0;
        }
        return o2.getSalary() > o1.getSalary() ? 1 : -1;
    }
}
