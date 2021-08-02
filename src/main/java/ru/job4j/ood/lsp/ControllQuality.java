package ru.job4j.ood.lsp;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllQuality implements FoodController {
    private final List<Container> containers = new ArrayList<>();
    private final Container warehouse;
    private final Container shop;
    private final Container trash;

    public ControllQuality(Container warehouse, Container shop, Container trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        containers.add(this.warehouse);
        containers.add(this.shop);
        containers.add(this.trash);
    }

    @Override
    public boolean doQualityCheck() {
        return (trash != null && shop != null && warehouse != null);
    }

    private void pushToWarehouse(List<Food> foodList) {
        warehouse.addFoodList(foodList);
    }

    private void pushToShop(List<Food> foodList) {
        shop.addFoodList(foodList);
    }

    private void pushToTrash(List<Food> foodList) {
        trash.addFoodList(foodList);
    }

    @Override
    public void resortFood() {
        sortFood(new ArrayList<>());
    }

    @Override
    public void addFood(Food food) {
        sortFood(List.of(food));
    }

    @Override
    public void sortFood(List<Food> foodList) {
        for (var conteiner : containers) {
            List<Food> foods = conteiner.getFoodList();
            if (!foods.isEmpty()) {
                foodList.addAll(foods);
            }
        }
        List<Food> toWarehouse = handler(0, 25, foodList);
        List<Food> toShop = handler(25, 75, foodList);
        List<Food> toShopWithDiscount = handler(75, 100, foodList);
        List<Food> toTrash = handler(100, 100, foodList);
        pushToWarehouse(toWarehouse);
        pushToShop(toShop);
        pushToTrash(toTrash);
        for (var item : toShopWithDiscount) {
            item.setDiscont(25);
        }
        pushToShop(toShopWithDiscount);
    }

    private List<Food> handler(int startPercent, int endPercent, List<Food> listFood) {
        List<Food> finishList = new ArrayList<>();
        for (var food : listFood) {
            Duration todayPeriod = Duration.between(food.getCreateDate().atStartOfDay(),
                    LocalDate.now().atStartOfDay());
            Duration foodPeriod = Duration.between(food.getCreateDate().atStartOfDay(),
                    food.getExpiryDate().atStartOfDay());
            long toTodayDays = todayPeriod.toDays();
            long fullDays = foodPeriod.toDays();
            double usePercents = (double) (toTodayDays * 100) / fullDays;
            if (!(usePercents < startPercent || usePercents >= endPercent)) {
                finishList.add(food);
            }
        }
        if (!finishList.isEmpty()) {
            return finishList;
        } else {
            return trashHandler(listFood);
        }
    }

    private List<Food> trashHandler(List<Food> listFood) {
        List<Food> finishList = new ArrayList<>();
        for (var food : listFood) {
            Duration todayPeriod = Duration.between(food.getCreateDate().atStartOfDay(),
                    LocalDate.now().atStartOfDay());
            Duration foodPeriod = Duration.between(food.getCreateDate().atStartOfDay(),
                    food.getExpiryDate().atStartOfDay());
            long toTodayDays = todayPeriod.toDays();
            long fullDays = foodPeriod.toDays();
            double usePercents = (double) (toTodayDays * 100) / fullDays;
            if (usePercents >= 100) {
                finishList.add(food);
            }
        }
        return finishList;
    }

    @Override
    public void showFoodState() {
        System.out.println("In Warehouse:");
        for (var item : warehouse.getFoodList())  {
            System.out.println("Name: " + item.getName());
            System.out.println("Discount " + item.getDiscont() + System.lineSeparator());
        }
        System.out.println("In Shop:");
        for (var item : shop.getFoodList())  {
            System.out.println("Name: " + item.getName());
            System.out.println("Discount " + item.getDiscont() + System.lineSeparator());
        }
        System.out.println("In Trash:");
        for (var item : trash.getFoodList())  {
            System.out.println("Name: " + item.getName());
            System.out.println("Discount " + item.getDiscont() + System.lineSeparator());
        }
    }
}
