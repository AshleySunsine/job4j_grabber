package ru.job4j.ood.cararking;

public class LightCar implements Car {

    private final String name;

    private final int size = 1;

    public LightCar(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
