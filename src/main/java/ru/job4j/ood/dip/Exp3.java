package ru.job4j.ood.dip;

public class Exp3 {
    class Person {
        String id;
        String name;
        String age;

        public Person(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public Person setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }

        public String getAge() {
            return age;
        }

        public Person setAge(String age) {
            this.age = age;
            return this;
        }

        public void born() {
            //Logic
        }
    }

    class Main {
        Person person = new Person("ds", "ag", "hjnhjm");
        /*
        Конкретные классы принято использовать, если они служат МОДЕЛЬЮ данных.
        Тут же есть нюанс - у модели есть поведение, а поведение может измениться.
        И это уже нарушает принцип DIP.
         */
    }
}
