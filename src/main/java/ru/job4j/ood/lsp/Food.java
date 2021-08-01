package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.Date;

abstract class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private int price;
    private int discont;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, int price, int discont) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discont = discont;
    }

    public String getName() {
        return name;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Food setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Food setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Food setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getDiscont() {
        return discont;
    }

    public Food setDiscont(int discont) {
        this.discont = discont;
        return this;
    }
}
