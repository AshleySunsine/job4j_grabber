package ru.job4j.ood.cararking;

import java.util.Map;

public interface ParkController {

    boolean inputCar(Car car);

    Car getCar(String name);

    Map<String, Integer> getFreePlace();
}
