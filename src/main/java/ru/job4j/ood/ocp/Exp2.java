package ru.job4j.ood.ocp;

public class Exp2 {
    class Car {
        String typeOfTire;

        int fuelValue;

        public void move() {

        }
    }

    class Tank extends Car {
        /*
        Казалось бы всё не плохо,
        но при наследовании, наследуется состояние объекта предка.
        У танка нет колёс, следовательно нет типа резины у них.
        Такое поле будет лишним.
        Лучше определить абстрактный класс SelfPropelledGun ("Самоходка")
        Там обозначить общие поля и методы.
         */
    }
}
