package ru.job4j.ood.dip;

public class Exp1 {
    class Cat1 {
        String id;
        String name;

        public Cat1(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void sound() {
            System.out.println("Как вам, наверное, надоели эти "
                    + "примеры с котиками, собачками и птичками...");
        }
    }

    class Zoo {
        Cat1 cat = new Cat1("Но я не могу",
                "больше ничего придумать");
    }

    /*
    Здесь класс зоопарка зависит от реализации
    класса животного. Если животное поменяет своё поведение,
    то может пострадать логика класса зоопарка.
     */
}
