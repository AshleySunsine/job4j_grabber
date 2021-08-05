package ru.job4j.ood.cararking;

import java.util.Map;
import java.util.Objects;

public class ParkOne implements Park {
    private final int light;
    private final int hard;

    public ParkOne(int light, int hard) {
        this.light = light;
        this.hard = hard;
    }

    @Override
    public boolean inputCar(Car car) {
        return false;
    }

    @Override
    public Map<String, Integer> getFreePlace() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkOne parkOne = (ParkOne) o;
        return light == parkOne.light && hard == parkOne.hard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(light, hard);
    }
}
