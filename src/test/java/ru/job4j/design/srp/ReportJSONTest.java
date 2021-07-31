package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import java.util.Calendar;

public class  ReportJSONTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        final Gson gson = new GsonBuilder().create();
        Calendar now1 = Calendar.getInstance();
        Calendar now2 = Calendar.getInstance();
        now1.set(2001, 11, 4);
        now2.set(2041, 2, 8);
        Employee worker = new Employee("Ivan", now1, now2, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                       .append("[{\"name\":\"Ivan\",\"hired\":")
                       .append(gson.toJson(worker.getHired()))
                       .append(",\"fired\":")
                       .append(gson.toJson(worker.getFired()))
                       .append(",\"salary\":100.0}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}