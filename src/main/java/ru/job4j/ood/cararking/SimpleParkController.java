package ru.job4j.ood.cararking;

import java.util.Map;
import java.util.Optional;

public class SimpleParkController implements ParkController {
    private final Park park;

    public SimpleParkController(Park park) {
        this.park = park;
    }

    @Override
    public boolean inputCar(Car car) {
        return park.inputCar(car);
    }

    @Override
    public Optional<Car> getCar(String name) {
        return park.getCar(name);
    }

    @Override
    public Map<String, Integer> getFreePlace() {
        return park.getFreePlace();
    }
}
