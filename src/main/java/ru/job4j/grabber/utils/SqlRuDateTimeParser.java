package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        List<String> months = new ArrayList<>(Arrays.asList("янв", "фев", "мар",
                "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"));
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
                int monthRusToInt = months.indexOf(dateRus[1]) + 1;
                int day = Integer.parseInt(dateRus[0]);
                LocalDate date = LocalDate.of(year, monthRusToInt, day);
                time = LocalDateTime.of(date, LocalTime.parse(parseArr[1]));
            }
        }
        return time;
    }

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements time = document.select(".forumtable").select("tr");
        Elements row = document.select(".postslisttopic");
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
        for (int i = 0; i < row.size(); i++) {
            Element href = row.get(i).child(0);
            Element t = time.get(i + 1).child(5);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(parser.parse(t.text()).format(formatter));
        }
    }
}