package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodHandler {

    public static void main(String[] args) {
        //trash
        Food banana = new BananaFood("banana", LocalDate.of(2021, 1, 2),
                LocalDate.of(2021, 8, 1), 200, 0);
        //Warehouse
        Food apple = new AppleFood("apple", LocalDate.of(2021, 7, 25),
                LocalDate.of(2022, 8, 1), 200, 0);
        //shopDiscount
        Food orange = new OrangeFood("orange", LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 9, 1), 200, 0);
        //shop
        Food oldOrange = new OrangeFood("Old orange", LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 12, 1), 200, 0);

        List<Food> foodList = new ArrayList<>();
        foodList.add(banana);
        foodList.add(apple);
        foodList.add(orange);
        foodList.add(oldOrange);

        Container shop = new ShopContainer();
        Container warehouse = new WarehouseContainer();
        Container trash = new TrashContainer();
        FoodController foodController = new ConcreteControlQualityBuilder()
                .setShopContainer(shop)
                .setTrashContainer(trash)
                .setWarehouseContainer(warehouse)
                .build();

        foodController.sortFood(foodList);
        foodController.showFoodState();
        /*
        after time
         */
        foodController.resortFood();
    }
}
