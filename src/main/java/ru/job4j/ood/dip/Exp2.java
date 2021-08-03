package ru.job4j.ood.dip;

public class Exp2 {
    String something;
    /*
    А тут казалось бы мы постоянно используем конкретный класс String?
    Но принцип SOLID не про твёрдое следование основным принципам.
    Это ориентир на который нужно равняться.
    Вывод: Анилизируем наличие вероятности изменения класса в будущем и,
    если она есть, то лучше создать интерфейс или абстрактный класс.
    А тут у нас String вероятность его изменения КРАЙНЕ МАЛА!
     */
}