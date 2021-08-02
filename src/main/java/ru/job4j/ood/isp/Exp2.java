package ru.job4j.ood.isp;

public class Exp2 {
    interface Pet {
        void fly();

        void run();

        void sound();
    }

    class dog implements Pet {
        @Override
        public void fly() {
            /*
            Ну зачем собаке реализовывать интерфейс полёта?
            Собаки же не летают... они парят!
             */
        }

        @Override
        public void run() {

        }

        @Override
        public void sound() {

        }
    }
}
