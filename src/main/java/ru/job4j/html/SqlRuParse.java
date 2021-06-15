package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".forumtable");
        for (Element td : row) {
            Element line = td.child(0);
            Element postslinetopic = line.child(1).child(1).child(0);
            Element date = line.child(1).child(5);
           System.out.println(postslinetopic.attr("href"));
            System.out.println(postslinetopic.text());
            System.out.println(date.text());
        }
    }
}