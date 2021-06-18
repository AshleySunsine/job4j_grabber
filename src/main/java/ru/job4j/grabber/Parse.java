package ru.job4j.grabber;

import java.util.List;
import ru.job4j.grabber.utils.Post;

public interface Parse {
    List<Post> list(String link);

    Post detail(String link);
}