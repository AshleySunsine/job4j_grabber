package ru.job4j.ood.cararking;

import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class ParkControllerTest {

    @Test
    public void inputLightCar() {
        Park park = new ParkOne(2, 1);
        ParkController controller = new SimpleParkController(park);
        Car lightOne = new LightCar("LightOne");
        Car lightTwo = new LightCar("LightTwo");
        controller.inputCar(lightOne);
        controller.inputCar(lightTwo);
        Map<String, Integer> expect = new HashMap<>();
        expect.put("Light", 0);
        expect.put("Hard", 1);
        Assert.assertEquals(expect, controller.getFreePlace());
    }

    @Test
    public void inputHardCar() {
        Park park = new ParkOne(0, 1);
        ParkController controller = new SimpleParkController(park);
        Car hard = new HardCar("HardOne", 2);
        controller.inputCar(hard);
        Map<String, Integer> expect = new HashMap<>();
        expect.put("Hard", 0);
        expect.put("Light", 0);
        Assert.assertEquals(expect, controller.getFreePlace());
    }

    @Test
    public void inputHardToLightCar() {
        Park park = new ParkOne(2, 0);
        ParkController controller = new SimpleParkController(park);
        Car hard = new HardCar("HardOne", 2);
        controller.inputCar(hard);
        controller.inputCar(hard);
        Map<String, Integer> expect = new HashMap<>();
        expect.put("Hard", 0);
        expect.put("Light", 0);
        Assert.assertEquals(expect, controller.getFreePlace());
    }

    @Test
    public void inputHardToLightCarWithoutPlace() {
        Park park = new ParkOne(0, 1);
        ParkController controller = new SimpleParkController(park);
        Car hard = new HardCar("HardOne", 2);
        controller.inputCar(hard);
        boolean expect = controller.inputCar(hard);
        Assert.assertFalse(expect);
    }

    @Test
    public void inputLightToLightCarWhithoutPlace() {
        Park park = new ParkOne(1, 0);
        ParkController controller = new SimpleParkController(park);
        Car light = new LightCar("LightOne");
        controller.inputCar(light);
        boolean expect = controller.inputCar(light);
        Assert.assertFalse(expect);
    }

    @Test
    public void removeCar() {
        Park park = new ParkOne(1, 0);
        ParkController controller = new SimpleParkController(park);
        Car light = new LightCar("LightOne");
        controller.inputCar(light);
        controller.getCar("LightOne");
        Map<String, Integer> expect = new HashMap<>();
        expect.put("Light", 0);
        expect.put("Hard", 0);
        Assert.assertEquals(expect, controller.getFreePlace());
    }

    @Test
    public void getCar() {
        Park park = new ParkOne(1, 0);
        ParkController controller = new SimpleParkController(park);
        Car light = new LightCar("LightOne");
        controller.inputCar(light);
        Car expect = new LightCar("LightOne");
        Car car = controller.getCar("LightOne").get();
        Assert.assertEquals(expect.getName(), car.getName());
    }
}