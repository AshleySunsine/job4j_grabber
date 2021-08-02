package ru.job4j.ood.lsp;

import java.util.List;

public interface FoodController {

    boolean doQualityCheck();

    void sortFood(List<Food> foodList);

    void addFood(Food food);

    void resortFood();

    void showFoodState();
}
