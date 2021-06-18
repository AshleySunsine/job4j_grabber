package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.Post;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlRuParse implements Parse {
    private static final List<String> MONTHS
            = new ArrayList<>(Arrays.asList("янв", "фев", "мар",
            "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"));

    private static LocalDateTime parseDate(String dateText) {
        LocalDateTime time;
        String[] parseArr = dateText.split(", ");
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

    @Override
    public List<Post> list(String link) {
        List<Post> outPosts = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();
            Elements timeElements = document.select(".forumtable").select("tr");
            Elements row = document.select(".postslisttopic");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
            for (int i = 0; i < row.size(); i++) {
                Element href = row.get(i).child(0);
                Element time = timeElements.get(i + 1).child(5);
                Post post = new Post();
                post.setLink(href.attr("href"));
                post.setName(href.text());
                post.setDateCreated(parseDate(time.text())
                        .format(formatter));
                outPosts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPosts;
        }

    @Override
    public Post detail(String link) {
        Post post = new Post();
        try {
            Document document = Jsoup.connect(link).get();
            post.setDescription(document
                    .select("table.msgTable:nth-child(3) > "
                            + "tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text());
            post.setName(document.select(".messageHeader").get(0).text());
            post.setDateCreated(document
                    .select("table.msgTable:nth-child(3) > tbody:nth-child(1) "
                            + "> tr:nth-child(3) > td:nth-child(1)").text().substring(0, 15));
            post.setLink(link);
            post.setId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post.toString());
        return post;
    }
}
