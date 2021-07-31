package ru.job4j.ood.ocp;

public class Exp1 {
    class Cat {
        public void sound() {}

        public void movie() {}
    }

    class Parrot extends Cat{
        public void giveFeathers() {}
    }

    /* Так делать нельзя т.к попугай НЕ кот.
    И при изменении поведения кота мы нарываемся на изменение
    поведения попугая.
    Следует обозначить интерфейс Pet или абстрактный класс Pet,
    где описать общие вещи для этих двух сущностей.
     */
}
