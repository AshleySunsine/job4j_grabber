package ru.job4j.ood.cararking;

import java.util.Map;

public class SimpleParkController implements ParkController {
    private final Park park;

    public SimpleParkController(Park park) {
        this.park = park;
    }

    @Override
    public boolean inputCar(Car car) {
        return false;
    }

    @Override
    public Car getCar(String name) {
        return null;
    }

    @Override
    public Map<String, Integer> getFreePlace() {
        return null;
    }
}
