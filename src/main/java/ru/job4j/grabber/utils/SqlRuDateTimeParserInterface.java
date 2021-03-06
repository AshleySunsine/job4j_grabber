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

public class SqlRuDateTimeParserInterface implements DateTimeParserInterface {

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

    public List<String> parsePage(String page, SqlRuDateTimeParserInterface parser)
            throws Exception {
        List<String> outText = new ArrayList<>();
        Document document = Jsoup.connect(page).get();
        Elements time = document.select(".forumtable").select("tr");
        Elements row = document.select(".postslisttopic");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
        for (int i = 0; i < row.size(); i++) {
            Element href = row.get(i).child(0);
            Element t = time.get(i + 1).child(5);
            String construct = href.attr("href") + System.lineSeparator()
                    + href.text() + System.lineSeparator()
                    + parser.parseDataTime(t.text()).format(formatter);
            outText.add(construct);
        }
        return outText;
    }

    public Post postDetail(String link) throws Exception {
        Post post = new Post();
        Document document = Jsoup.connect(link).get();
        post.setDescription(document
                .select("table.msgTable:nth-child(3) > "
                        + "tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text());
        post.setName(document.select(".messageHeader").get(0).text());
        post.setDateCreated(parseDataTime(document
                .select("table.msgTable:nth-child(3) > tbody:nth-child(1) "
                        + "> tr:nth-child(3) > td:nth-child(1)").text().substring(0, 15)));
        post.setLink(link);
        post.setId(1);
        System.out.println(post.toString());
        return post;
    }

    public static void main(String[] args) throws Exception {
        SqlRuDateTimeParserInterface parser = new SqlRuDateTimeParserInterface();
        for (int i = 1; i < 6; i++) {
            String page = String.format("https://www.sql.ru/forum/job-offers/%d", i);
            System.out.println(page);
            System.out.println(parser.parsePage(page, parser));
        }
        System.out.println(parser
                .postDetail("https://www.sql.ru/forum/1334597/administrator-baz-dannyh-dba"));
    }
}