package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateTimeParser implements DateTimeParserInterface {
    private static final List<String> MONTHS
            = new ArrayList<>(Arrays.asList("янв", "фев", "мар",
            "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"));

    @Override
    public LocalDateTime parseDataTime(String parse) {
        LocalDateTime time;
        String[] parseArr = parse.split(", ");
        if (parseArr[0].equals("вчера")) {
            time = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.parse(parseArr[1]));
        } else {
            if (parseArr[0].equals("сегодня")) {
                time = LocalDateTime.of(LocalDate.now(), LocalTime.parse(parseArr[1]));
            } else {
                String[] dateRus = parseArr[0].split(" ");
                int year = Integer.parseInt(dateRus[2]);
                int monthRusToInt = MONTHS.indexOf(dateRus[1]) + 1;
                int day = Integer.parseInt(dateRus[0]);
                LocalDate date = LocalDate.of(year, monthRusToInt, day);
                time = LocalDateTime.of(date, LocalTime.parse(parseArr[1]));
            }
        }
        return time;
    }
}
