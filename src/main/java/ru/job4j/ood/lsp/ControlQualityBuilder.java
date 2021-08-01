package ru.job4j.ood.lsp;

interface ControlQualityBuilder {
    ControlQualityBuilder setTrashContainer(Container trashContainer);

    ControlQualityBuilder setShopContainer(Container shopContainer);

    ControlQualityBuilder setWarehouseContainer(Container warehouseContainerContainer);

    FoodController build();
}
