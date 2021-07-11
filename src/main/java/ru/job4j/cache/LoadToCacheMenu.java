package ru.job4j.cache;

import java.util.Scanner;

public class LoadToCacheMenu implements MenuInterface {
    @Override
    public String nameMenu() {
        return "Загрузить файл в кэш";
    }

    @Override
    public void run(Emulator emulator) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите имя файла для загрузки:" + System.lineSeparator());
        String file = scanner.nextLine();
        emulator.loadToCache(file);
    }
}
