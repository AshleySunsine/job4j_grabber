package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Exp1 {

   static class Rectangle {
        int a;
        int b;

        public Rectangle(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public Rectangle setA(int a) {
            this.a = a;
            return this;
        }

        public int getB() {
            return b;
        }

        public Rectangle setB(int b) {
            this.b = b;
            return this;
        }
    }

    static class Square extends Rectangle {
        public Square(int a, int b) {
            super(a, a);
        }
    }

    public static void main(String[] args) {
        List<Rectangle> rects = new ArrayList<>();
        Rectangle fig1 = new Rectangle(21, 45);
        Rectangle fig2 = new Rectangle(2, 5);
        Square fig3 = new Square(21, 55);
        Rectangle fig4 = new Rectangle(8, 9);
        rects.add(fig1);
        rects.add(fig2);
        rects.add(fig3);
        rects.add(fig4);

        for (var item : rects) {
            item.setB(8);
            /* Здесь получается косяк, потому что
            при наследовании от прямоугольника мы не переопределили
            геттеры и сеттеры по стороне b.
            Если еще ктото захочет наследоваться от Square то тут начнётся кошмар.
            Дочерний класс должен иметь такой же интерфейс, как и родительский!
            */
        }
    }
}
