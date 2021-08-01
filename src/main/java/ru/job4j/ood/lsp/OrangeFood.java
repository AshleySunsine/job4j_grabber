package ru.job4j.ood.lsp;

import java.time.LocalDate;

public class OrangeFood extends Food {
    public OrangeFood(String name, LocalDate expiryDate, LocalDate createDate, int price, int discont) {
        super(name, expiryDate, createDate, price, discont);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Food setName(String name) {
        return super.setName(name);
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getExpiryDate();
    }

    @Override
    public Food setExpiryDate(LocalDate expiryDate) {
        return super.setExpiryDate(expiryDate);
    }

    @Override
    public LocalDate getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public Food setCreateDate(LocalDate createDate) {
        return super.setCreateDate(createDate);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public Food setPrice(int price) {
        return super.setPrice(price);
    }

    @Override
    public int getDiscont() {
        return super.getDiscont();
    }

    @Override
    public Food setDiscont(int discont) {
        return super.setDiscont(discont);
    }
}
