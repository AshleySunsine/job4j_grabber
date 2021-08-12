package ru.job4j.ood.cararking;

public class HardCar implements Car {
    private int size;
    private String name;

    public HardCar(String name, int size) {
        if (size >= 2) {
            this.size = size;
            this.name = name;
        } else {
            throw new IllegalArgumentException("Размер должен быть больше двух!");
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
