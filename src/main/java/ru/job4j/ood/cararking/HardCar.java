package ru.job4j.ood.cararking;

public class HardCar implements Car {
    private int size;

    public HardCar(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
