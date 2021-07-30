package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        final Gson gson = new GsonBuilder().create();
        for (Employee employee : store.findBy(filter)) {
             text.append(gson.toJson(employee)).append(";")
                    .append(System.lineSeparator());
        }
        System.out.println(text);
        return text.toString();
    }
}