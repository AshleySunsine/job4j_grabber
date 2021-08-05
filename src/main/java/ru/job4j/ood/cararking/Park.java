package ru.job4j.ood.cararking;

import java.util.Map;

public interface Park {

    Map<String, Integer> getFreePlace();

    boolean inputCar(Car car);
}
