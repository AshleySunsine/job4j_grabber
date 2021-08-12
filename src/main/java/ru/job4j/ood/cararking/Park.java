package ru.job4j.ood.cararking;

import java.util.Map;
import java.util.Optional;

public interface Park {
    boolean inputCar(Car car);

    Optional<Car> getCar(String name);

    Map<String, Integer> getFreePlace();
}
