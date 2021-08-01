package ru.job4j.ood.lsp;

import java.util.*;

abstract class Container {
    private List<Food> foodList = new ArrayList<>();

    public Container() {
    }

    public List<Food> addFoodList(List<Food> foodList) {
        this.foodList.addAll(foodList);
        return this.foodList;
    }

    public Food addFood(Food food) {
        if (food != null) {
            foodList.add(food);
            return food;
        } else {
            throw new IllegalArgumentException("food is NULL");
        }
    }

    public List<Food> getFoodList() {
        return foodList;
    }

}
