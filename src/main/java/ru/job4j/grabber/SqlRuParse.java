package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> outPosts = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();
            Elements timeElements = document.select(".forumtable").select("tr");
            Elements row = document.select(".postslisttopic");
            for (int i = 0; i < row.size(); i++) {
                Element href = row.get(i).child(0);
                Element time = timeElements.get(i + 1).child(5);
                Post post = new Post();
                post.setLink(href.attr("href"));
                post.setName(href.text());
                post.setDateCreated(dateTimeParser.parseDataTime(time.text()));
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
            post.setLink(link);
            post.setId(1);
            String[] textForExplitDate = document
                    .select(".msgFooter").get(0).text().split(", ");
            String expliteDateTime = textForExplitDate[0]
                    + ", "
                    + textForExplitDate[1].substring(0, 5);

            LocalDateTime expliteDataTime =
                    dateTimeParser.parseDataTime(expliteDateTime);
            post.setDateCreated(expliteDataTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }
}
