package ru.job4j.grabber.utils;

public class Post {
    private int id;
    private String name;
    private String description;
    private String link;
    private String dateCreated;

    public Post() {
    }

    public Post(int id, String name, String description, String link, String dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.dateCreated = dateCreated;
    }

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

    public String getDateCreated() {
        return dateCreated;
    }

    public Post setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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
                + ", dateCreated='" + dateCreated + '\''
                + '}';
    }
}
