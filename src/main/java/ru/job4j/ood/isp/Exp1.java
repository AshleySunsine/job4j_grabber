package ru.job4j.ood.isp;

public interface Exp1 {
    void drawLine();

    void drawCube();

    void drawInspectorGadget();

    void drawUniverse();

    void drawFish();

    void drawYou();

    void kickSomeone();
/*
Здесь явно интерфейс перегружен.
Не все методы могут понадобиться пользователю.
Лучше много интерфейсов, чем один с бесполезными методами.
 */
}
