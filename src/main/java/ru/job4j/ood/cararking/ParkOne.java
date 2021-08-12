package ru.job4j.ood.cararking;

import java.util.*;

public class ParkOne implements Park {
    private int light;
    private int hard;
    private final Car[] lightPark;
    private final Car[] hardPark;

    public ParkOne(int light, int hard) {
        this.light = light;
        this.hard = hard;
        this.lightPark = new Car[light];
        this.hardPark = new Car[hard];
    }

    @Override
    public boolean inputCar(Car car) {
        int carSize = car.getSize();
        int freePlaceIndex;
        if (carSize == 1) {
            freePlaceIndex = getFreeIndex(lightPark, 1);
            if (freePlaceIndex != (-1)) {
                lightPark[freePlaceIndex] = car;
                light--;
                return true;
            } else {
                System.out.println("Парковка легковых заполнена.");
            }
        } else {
            if ((hard > (hardPark.length - 1)) && (hard > 0)) {
                freePlaceIndex = getFreeIndex(hardPark, 1);
                if (freePlaceIndex != (-1)) {
                    hardPark[freePlaceIndex] = car;
                    hard--;
                    return true;
                }
            } else {
                System.out.println("Парковка грузовых заполнена.");
                freePlaceIndex = getFreeIndex(lightPark, carSize);
                if (freePlaceIndex != (-1)) {
                    lightPark[freePlaceIndex] = car;
                    light = light - carSize;
                } else {
                    System.out.println("Все парковки заняты");
                }
            }
        }
        return false;
    }

    private int getFreeIndex(Car[] parking, int size) {
        int freePlaceCount = 0;
        for (Car car : parking) {
            if (car == null) {
                freePlaceCount++;
                if (freePlaceCount == size) {
                    return freePlaceCount - size;
                }
            } else {
                freePlaceCount = 0;
            }
        }
        return -1;
    }

    public Optional<Car> getCar(String name) {
        for (Car car : lightPark) {
            if (car.getName().equals(name)) {
                return Optional.of(car);
            }
        }
        for (Car car : hardPark) {
            if (car.getName().equals(name)) {
                return Optional.of(car);
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Integer> getFreePlace() {
        Map<String, Integer> freePlace = new HashMap<>();
        freePlace.put("Light", this.light);
        freePlace.put("Hard", this.hard);
        return freePlace;
    }

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
