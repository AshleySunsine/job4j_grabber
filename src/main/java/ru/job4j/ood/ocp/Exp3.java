package ru.job4j.ood.ocp;

public class Exp3 {
    interface Car {
        void movie();

        void setColor();
    }

    class BMVEngine {

        public void start() {

        };
    }

    class BMVCar implements Car {
        BMVEngine engine = new BMVEngine();

        @Override
        public void movie() {

        }

        @Override
        public void setColor() {

        }
    }

    /*
    Всё бы ничего: и интерфейс есть, и свойства объекта не мешают.
    Проблема в том, что внутри класса BMVCar мы жестко зависим от класса BMVEngine.
    Если BMVEngine изменится, то всё "поплывёт".
    Следовательно внутренние поля зависящие от сторонних классов лучше сделать зависящими
    от интерфейсов.
     */
}
