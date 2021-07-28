package ru.job4j.srp;

import java.util.Objects;

public class Accaunt {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public Accaunt setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Accaunt setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accaunt accaunt = (Accaunt) o;
        return age == accaunt.age && Objects.equals(name, accaunt.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public void saveToBase() {
        /*
        Этот метод здесь лишний, т.к это
        класс описывающий аккаунт, а
        сохранение в базу должен описавать
        класс-контекст.
         */
    }
}
