package ru.job4j.ood.lsp;

public class ConcreteControlQualityBuilder implements ControlQualityBuilder {
    private Container trash;
    private Container shop;
    private Container warehouse;

    @Override
    public ControlQualityBuilder setTrashContainer(Container trashContainer) {
        this.trash = trashContainer;
        return this;
    }

    @Override
    public ControlQualityBuilder setShopContainer(Container shopContainer) {
        this.shop = shopContainer;
        return this;
    }

    @Override
    public ControlQualityBuilder setWarehouseContainer(Container warehouseContainerContainer) {
        this.warehouse = warehouseContainerContainer;
        return this;
    }

    @Override
    public FoodController build() {
        FoodController controller = new ControllQuality(warehouse, shop, trash);
        if (controller.doQualityCheck()) {
            return controller;
        } else {
            System.out.println("!!! Controller assembly is incomplete !!!");
        }
        return null;
    }
}
