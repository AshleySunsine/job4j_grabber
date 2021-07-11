package ru.job4j.cache;

import java.util.Scanner;

public class ChangeDirMenu implements MenuInterface {
    @Override
    public String nameMenu() {
        return "Сменить директорию";
    }

    @Override
    public void run(Emulator emulator) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите новую директорию: " + System.lineSeparator());
        String dir = scanner.nextLine();
        emulator.changeFileDirectory(dir);
    }
}
