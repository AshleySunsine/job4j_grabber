package ru.job4j.ood.lsp;

public class Exp3 {
    enum GenderType {
        MAN, WOMAN, HALFWUMAN, HALFMAN, MORGENSHTERN;
    }

    public void getText(GenderType gender) {
        if (gender == GenderType.MAN) {
            System.out.println("MAN!");
        }
        if (gender == GenderType.WOMAN) {
             System.out.println("WOMAN!");
        }
        if (gender == GenderType.HALFWUMAN) {
            System.out.println("ACHTUNG!");
        }
        /*
        Если дальше так пойдёт, то нам придётся дописывать
        этот класс/метод для работы с новыми "людьми".
        Между делом это нарушает еще и принцип открытости/закрытости.
        Если мы один раз написали класс, то всё, к нему больше не возвращаемся.
        "В enum должны быть константы, которые в будущем не изменяться и не пополняться."
         */
    }

}
