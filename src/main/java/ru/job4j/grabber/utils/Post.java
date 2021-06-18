package ru.job4j.grabber.utils;

public class Post {
    private int id;
    private String name;
    private String description;
    private String link;
    private String date;

    public String getDescription() {
        return description;
    }

    public Post setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Post setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Post setDate(String date) {
        this.date = date;
        return this;
    }

    public int getId() {
        return id;
    }

    public Post setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Post setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", link='" + link + '\''
                + ", date='" + date + '\''
                + '}';
    }
}
